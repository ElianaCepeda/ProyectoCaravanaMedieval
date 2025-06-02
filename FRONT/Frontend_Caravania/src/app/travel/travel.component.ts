// src/app/travel/travel.component.ts
import { Component, OnInit } from '@angular/core';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { RutasService, RutaDTO } from '../services-back/rutas.service';
import { CaravanaService } from '../services-back/caravana.service';
import { Ciudad } from '../Models/ciudad';
import { Caravana } from '../Models/caravana';
import { ClockService } from '../services-back/clock.service'; 

interface Adjacent {
  city: Ciudad;
  route: { id: number; dano: number; descripcion: string; ciudadDestinoId: number; };
}

@Component({
  selector: 'app-travel',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './travel.component.html',
  styleUrls: ['./travel.component.css']
})
export class TravelComponent implements OnInit {
  vidaActual = 0;
  dineroActual = 0;

  originCityId!: number;
  actualCity: Ciudad | null = null;
  adjacent: Adjacent[] = [];

  // Para almacenar la caravana completa (incluyendo ciudadActualId)
  caravana: Caravana | null = null;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ciudadService: CiudadService,
    private rutasService: RutasService,
    private caravanaService: CaravanaService,
    private clockService: ClockService
  ) { }

  ngOnInit(): void {
    // 1) Primero cargo la caravana para sincronizar vida, dinero y ciudad actual
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: c => {
        this.caravana = c;
        this.vidaActual = c.vidas;
        this.dineroActual = c.dinero;

        // 2) Luego leo el parámetro ?origen=ID 
        this.route.queryParams.subscribe(params => {
          const paramId = Number(params['origen']);
          
          // Usar el parámetro si existe, sino usar la ciudad actual de la caravana
          this.originCityId = (!isNaN(paramId) && paramId) ? paramId : c.ciudadActualId || 1;

          // Actualizar la ciudad actual de la caravana si viene por parámetro
          if (!isNaN(paramId) && paramId) {
            this.caravana!.ciudadActualId = paramId;
          }

          // Cargar ciudad actual y sus adyacentes
          this.loadCityAndAdjacent();
        });
      },
      error: err => console.error('Error cargando caravana:', err)
    });
  }

  private loadCityAndAdjacent() {
    if (!this.originCityId) {
      console.error('No se pudo determinar la ciudad de origen');
      return;
    }

    // Cargar la ciudad actual
    this.ciudadService.obtenerCiudad(this.originCityId).subscribe({
      next: ciudad => {
        this.actualCity = ciudad;
        this.loadAdjacent();
      },
      error: err => console.error('Error al cargar ciudad actual:', err)
    });
  }

  private loadAdjacent() {
    // Limpiar array anterior para evitar duplicados
    this.adjacent = [];
    
    this.rutasService.obtenerRutas().subscribe({
      next: (rutas: RutaDTO[]) => {
        // Filtrar rutas que partan desde originCityId
        const rutasFiltered = rutas.filter(r => r.ciudadOrigenId === this.originCityId);
        
        // Contador para controlar la carga asíncrona
        let ciudadesPendientes = rutasFiltered.length;
        
        if (ciudadesPendientes === 0) {
          console.log('No hay ciudades adyacentes para la ciudad:', this.originCityId);
          return;
        }

        rutasFiltered.forEach(r => {
          // Por cada ruta, pedimos la ciudad destino
          this.ciudadService.obtenerCiudad(r.ciudadDestinoId).subscribe({
            next: dest => {
              // Verificar que no esté ya en la lista (evitar duplicados)
              const yaExiste = this.adjacent.find(adj => adj.city.id === dest.id);
              
              if (!yaExiste) {
                this.adjacent.push({
                  city: dest,
                  route: {
                    id: r.id,
                    dano: r.cantidad_dano,
                    descripcion: r.descripcion_dano,
                    ciudadDestinoId: r.ciudadDestinoId
                  }
                });
              }
              
              ciudadesPendientes--;
              
              if (ciudadesPendientes === 0) {
                console.log('Ciudades adyacentes cargadas:', this.adjacent.length);
              }
            },
            error: err => {
              console.error('Error al cargar destino:', err);
              ciudadesPendientes--;
            }
          });
        });
      },
      error: err => console.error('Error al cargar rutas:', err)
    });
  }

  travelTo(routeId: number) {
    // 1) Encontrar la ruta en la lista de adyacentes
    const sel = this.adjacent.find(a => a.route.id === routeId);
    if (!sel) {
      console.error('Ruta no encontrada:', routeId);
      return;
    }

    // 2) Calcular daño y/o costo de tarifa
    const costoTarifa = sel.city.tarifa;
    const dañoRuta = sel.route.dano;
    const ciudadDestinoId = sel.route.ciudadDestinoId;

    // 3) Validar que hay suficiente dinero para la tarifa
    if (this.caravana && this.caravana.dinero < costoTarifa) {
      alert(`No tienes suficiente oro para pagar la tarifa de ${costoTarifa} en ${sel.city.nombre}.`);
      return;
    }

    // 4) Actualizar la caravana localmente (vidas, dinero y ciudadActualId)
    if (this.caravana) {
      
      const dañoReal = this.caravana.guardias ? 0 : dañoRuta;
      this.caravana.vidas = Math.max(0, this.caravana.vidas - dañoReal);
      this.caravana.dinero = Math.max(0, this.caravana.dinero - costoTarifa);
      this.caravana.ciudadActualId = ciudadDestinoId;

      const v = this.caravana.velocidad_actual;
      // Lineal entre 3h (v=1) y 1h (v=8)
      let horasViaje = 3 - ((v - 1) * (2 / 7));
      horasViaje = Math.ceil(horasViaje); // Redondea hacia arriba

      // Sumar horas al reloj ficticio
      this.clockService.sumarHoras(horasViaje);
  

      // Crear objeto para enviar (sin ciudadActualId si el backend no lo acepta)
      const { ciudadActualId, ...caravanaParaEnviar } = this.caravana;

      // 5) Hacer llamada al backend para persistir el cambio
      this.caravanaService.actualizarCaravana(this.caravana.id, caravanaParaEnviar).subscribe({
        next: updated => {
          console.log('Caravana actualizada, viajando a:', sel.city.nombre);
          // 6) Redirigir al mapa, pasando la ciudadDestinoId como ?actual=ID
          this.router.navigate(['/mapCaravania'], {
            queryParams: { actual: ciudadDestinoId }
          });
        },
        error: err => {
          console.error('Error al actualizar caravana:', err);
          // Aún así, redirijo pero sin actualizar backend:
          this.router.navigate(['/mapCaravania'], {
            queryParams: { actual: ciudadDestinoId }
          });
        }
      });

    } else {
      console.error('Caravana no cargada aún.');
    }
  }

  btnClick() {
    // Para "Devolverse", enviamos la misma cityId de origen para que el mapa pinte la última
    if (this.originCityId) {
      this.router.navigate(['/mapCaravania'], {
        queryParams: { actual: this.originCityId }
      });
    } else {
      this.router.navigate(['/mapCaravania']);
    }
  }
}