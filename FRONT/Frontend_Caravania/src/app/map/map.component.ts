import { Component, OnInit, AfterViewInit, NgZone } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { Ciudad } from '../Models/ciudad';
declare var OpenSeadragon: any;

interface Zone { xMin: number; xMax: number; yMin: number; yMax: number; }

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [ CommonModule, RouterModule, PlayerUiComponent ],
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit {

  vidaActual = 100;
  dineroActual = 9999;

  actualCity: (Ciudad & { x: number; y: number }) | null = null;
  adjacentCities: Array<Ciudad & { x: number; y: number }> = [];

  // Definición de las 3 zonas verdes como rectángulos
  private readonly greenZones: readonly Zone[] = [
    { xMin: 3000, xMax: 8000, yMin: 11000, yMax: 5500 } , // Zona roja (Oeste)
    { xMin:  9500, xMax: 19500,  yMin: 11000, yMax: 5500}, // Zona azul (Centro)
    { xMin: 19500, xMax: 24500,  yMin:  3500, yMax: 10500 }, // Zona morada (Este)
    { xMin: 24500, xMax: 28500,  yMin:  5000, yMax: 11000 }  // Zona rosa (Extremo este)
  ];
  

  constructor(
    private router: Router,
    private ngZone: NgZone,
    private ciudadService: CiudadService
  ) {}

  ngOnInit(): void {
    this.ciudadService.obtenerCiudades().subscribe({
      next: (data: Ciudad[]) => {
        // 1) Asignar coords aleatorias dentro de una zona verde
        const withCoords = data.map(ciudad => ({
          ...ciudad,
          ...this.pickRandomGreenCoord()
        }));
        // 2) Elegir aleatoriamente una como ciudad actual
        if (withCoords.length > 0) {
          const idx = Math.floor(Math.random() * withCoords.length);
          this.actualCity = withCoords.splice(idx, 1)[0];
        }
        // 3) El resto son ciudades adyacentes
        this.adjacentCities = withCoords;
      },
      error: err => console.error('No se pudieron cargar ciudades:', err)
    });
  }

  ngAfterViewInit(): void {
    const viewer = OpenSeadragon({
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

    viewer.addHandler('open', () => {
      // Overlay para ciudad actual
      if (this.actualCity) {
        const el = document.getElementById('ciudad-actual')!;
        const coord = viewer.viewport.imageToViewportCoordinates(
          this.actualCity.x, this.actualCity.y
        );
        viewer.addOverlay({ element: el, location: coord, placement: OpenSeadragon.Placement.CENTER });
      }
      // Overlays para ciudades adyacentes
      this.adjacentCities.forEach((city, i) => {
        const el = document.getElementById(`ciudad-adyacente-${i}`)!;
        const coord = viewer.viewport.imageToViewportCoordinates(city.x, city.y);
        viewer.addOverlay({ element: el, location: coord, placement: OpenSeadragon.Placement.CENTER });
      });
    });
  }

  /** Navegaciones estándar */
  goCommerce() { this.router.navigate(['/commerce']); }
  goService()  { this.router.navigate(['/services']); }
  goTravel()   { this.router.navigate(['/travel']); }

  /** Elige al azar una zona y devuelve coords dentro de ella */
  private pickRandomGreenCoord(): { x: number; y: number } {
    const zone = this.greenZones[
      Math.floor(Math.random() * this.greenZones.length)
    ];
    const x = this.randomInt(zone.xMin, zone.xMax);
    const y = this.randomInt(zone.yMin, zone.yMax);
    return { x, y };
  }

  /** Helper: entero aleatorio en [min, max) */
  private randomInt(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min) + min);
  }
}
