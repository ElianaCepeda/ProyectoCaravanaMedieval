// src/app/map/map.component.ts
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { RutasService, RutaDTO } from '../services-back/rutas.service';
import { CaravanaService } from '../services-back/caravana.service';
import { ClockService } from '../services-back/clock.service';
import { Ciudad } from '../Models/ciudad';
import { Subscription } from 'rxjs';
declare var OpenSeadragon: any;

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit {
  vidaActual!: number;
  dineroActual!: number;

  // Ciudad actual con sus coordenadas
  actualCity: (Ciudad & { x: number; y: number }) | null = null;
  // Ciudades adyacentes (tienen x,y para overlay)
  adjacentCities: Array<Ciudad & { x: number; y: number }> = [];
  
  // Variables para controlar el estado
  private viewer: any = null;
  private viewerReady = false;
  private dataReady = false;

  
  displayTime: string = '00:00';            // Lo que se mostrará en el botón
  private clockSub!: Subscription;          // Para cancelar la suscripción

  getTiempoIcon(): string {
  
  const [hh, mm] = this.displayTime.split(':').map(Number);
  // Noche: 18:00 (6pm) hasta 5:59 (6am)
  if (hh >= 6 && hh < 18) {
    return '☀️'; // Día
  } else {
    return '🌙'; // Noche
  }
}

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ciudadService: CiudadService,
    private rutasService: RutasService,
    private caravanaService: CaravanaService,
    private clockService: ClockService       // ← Inyectamos el servicio
  ) {}

  ngOnInit(): void {

    this.clockSub = this.clockService.displayTime$.subscribe(time => {
      this.displayTime = time;
    });
    // 1) Primero, cargamos la caravana para mostrar vida y dinero
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: caravana => {
        this.vidaActual = caravana.vidas;
        this.dineroActual = caravana.dinero;
        
        // 2) Después de cargar la caravana, determinamos la ciudad actual
        this.route.queryParams.subscribe(params => {
          const idParam = Number(params['actual']);
          let cityId: number;
          
          if (idParam && !isNaN(idParam)) {
            // Si nos pasaron explícitamente la ciudad actual
            cityId = idParam;
          } else {
            // Si no vienen parámetros, usar la ciudad actual de la caravana
            cityId = caravana.ciudadActualId || 1; // fallback a 1 si no existe
          }
          
          this.loadCityAndAdjacents(cityId);
        });
      },
      error: err => console.error('Error cargando caravana:', err)
    });
    
  }

  private loadCityAndAdjacents(cityId: number) {
    console.log('Cargando ciudad y adyacentes para ID:', cityId);
    
    // 1) Cargar la ciudad actual (incluye x,y)
    this.ciudadService.obtenerCiudad(cityId).subscribe({
      next: (ciud: Ciudad & { x: number; y: number }) => {
        console.log('Ciudad actual cargada:', ciud);
        this.actualCity = ciud;

        // 2) Cargar todas las rutas para filtrar las adyacentes
        this.rutasService.obtenerRutas().subscribe({
          next: (rutas: RutaDTO[]) => {
            // Filtrar rutas donde ciudadOrigenId === cityId
            const rutasAdj = rutas.filter(r => r.ciudadOrigenId === cityId);
            console.log('Rutas adyacentes encontradas:', rutasAdj.length);

            // Limpiar array anterior
            this.adjacentCities = [];
            
            // Si no hay rutas adyacentes, marcar datos como listos
            if (rutasAdj.length === 0) {
              this.dataReady = true;
              this.tryUpdateOverlays();
              return;
            }

            // Contador para saber cuándo terminamos de cargar todas las ciudades
            let ciudadesPendientes = rutasAdj.length;

            // Para cada ruta, pedimos la ciudad destino completa (incluyendo x,y)
            rutasAdj.forEach(r => {
              this.ciudadService.obtenerCiudad(r.ciudadDestinoId).subscribe({
                next: ciudadDest => {
                  console.log('Ciudad adyacente cargada:', ciudadDest);
                  this.adjacentCities.push(ciudadDest);
                  ciudadesPendientes--;
                  
                  // Cuando terminamos de cargar todas las ciudades
                  if (ciudadesPendientes === 0) {
                    console.log('Todas las ciudades adyacentes cargadas');
                    this.dataReady = true;
                    this.tryUpdateOverlays();
                  }
                },
                error: err => {
                  console.error('Error al cargar ciudad adyacente:', err);
                  ciudadesPendientes--;
                  if (ciudadesPendientes === 0) {
                    this.dataReady = true;
                    this.tryUpdateOverlays();
                  }
                }
              });
            });
          },
          error: err => console.error('Error al cargar rutas:', err)
        });
      },
      error: err => console.error('Error al cargar ciudad actual:', err)
    });
  }

  ngAfterViewInit(): void {
    // Inicializar OpenSeadragon
    this.viewer = OpenSeadragon({
      id: "opensea-container",
      prefixUrl: "https://cdnjs.cloudflare.com/ajax/libs/openseadragon/2.4.2/images/",
      tileSources: "assets/img/map/mapa.dzi",
      showNavigator: true,
      navigatorPosition: "TOP_RIGHT",
      minZoomLevel: 1,
      defaultZoomLevel: 1,
      maxZoomPixelRatio: 2,
      constrainDuringPan: true,
      visibilityRatio: 0.96,
      immediateRender: false,
      blendTime: 0.1,
      imageSmoothingEnabled: true,
      maxImageCacheCount: 300,
      springStiffness: 5.0,
      gestureSettingsMouse: {
        clickToZoom: false,
        dblClickToZoom: false,
        scrollToZoom: true,
        pinchToZoom: true
      }
    });

    this.viewer.addHandler('open', () => {
      console.log('Viewer abierto correctamente');
      this.viewerReady = true;
      this.tryUpdateOverlays();
    });
  }


 

  // Método que solo actualiza overlays cuando tanto viewer como datos están listos
  private tryUpdateOverlays(): void {
    console.log('Intentando actualizar overlays - Viewer listo:', this.viewerReady, 'Datos listos:', this.dataReady);
    
    if (this.viewerReady && this.dataReady) {
      // Delay para asegurar que Angular haya renderizado los elementos DOM
      setTimeout(() => {
        this.updateOverlays();
      }, 200);
    }
  }

  private updateOverlays(): void {
    if (!this.viewer || !this.viewer.isOpen()) {
      console.log('Viewer no está listo para overlays');
      return;
    }

    console.log('Actualizando overlays...');
    
    // Limpiar overlays existentes
    this.viewer.clearOverlays();

    // 1) Overlay ciudad actual
    if (this.actualCity) {
      const elActual = document.getElementById('ciudad-actual');
      if (elActual) {
        console.log('Agregando overlay ciudad actual:', this.actualCity.nombre, 'en coordenadas:', this.actualCity.x, this.actualCity.y);
        
        // Verificar que las coordenadas sean válidas
        if (typeof this.actualCity.x === 'number' && typeof this.actualCity.y === 'number') {
          const coordActual = this.viewer.viewport.imageToViewportCoordinates(
            this.actualCity.x,
            this.actualCity.y
          );
          
          this.viewer.addOverlay({
            element: elActual,
            location: coordActual,
            placement: OpenSeadragon.Placement.CENTER
          });
          console.log('Overlay ciudad actual agregado exitosamente');
        } else {
          console.error('Coordenadas inválidas para ciudad actual:', this.actualCity.x, this.actualCity.y);
        }
      } else {
        console.error('Elemento ciudad-actual no encontrado en el DOM');
      }
    } else {
      console.log('No hay ciudad actual para mostrar');
    }

    // 2) Overlays ciudades adyacentes
    console.log('Agregando', this.adjacentCities.length, 'ciudades adyacentes');
    this.adjacentCities.forEach((city, i) => {
      const elAdj = document.getElementById(`ciudad-adyacente-${i}`);
      if (elAdj && typeof city.x === 'number' && typeof city.y === 'number') {
        const coordAdj = this.viewer.viewport.imageToViewportCoordinates(
          city.x,
          city.y
        );
        this.viewer.addOverlay({
          element: elAdj,
          location: coordAdj,
          placement: OpenSeadragon.Placement.CENTER
        });
        console.log(`Overlay ciudad adyacente ${i} agregado:`, city.nombre);
      } else {
        console.error(`Elemento ciudad-adyacente-${i} no encontrado o coordenadas inválidas`);
      }
    });

    console.log('Proceso de actualización de overlays completado');
  }

  // Navegar a Comercio
  goCommerce(): void {
    this.router.navigate(['/commerce']);
  }

  // Navegar a Servicios
  goService(): void {
    this.router.navigate(['/services']);
  }

  // Navegar a Travel pasando el ID de la ciudad actual
  goTravel(): void {
    if (!this.actualCity) return;
    this.router.navigate(['/travel'], {
      queryParams: { origen: this.actualCity.id }
    });
  }

  goCaravan(): void {
    this.router.navigate(['/caravan']);
  }

  ngOnDestroy(): void {
    // 1) Cancelar la suscripción del reloj
    if (this.clockSub) {
      this.clockSub.unsubscribe();
    }
    
  }
}