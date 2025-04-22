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

interface Adjacent {
  city:     Ciudad;
  route:    { id: number; dano: number; descripcion: string; };
}

@Component({
  selector: 'app-travel',
  standalone: true,
  imports: [ CommonModule, RouterModule, PlayerUiComponent ],
  templateUrl: './travel.component.html',
  styleUrls:   ['./travel.component.css']
})
export class TravelComponent implements OnInit {
  vidaActual   = 0;
  dineroActual = 0;

  originCityId!: number;
  actualCity:   Ciudad | null = null;
  adjacent:     Adjacent[]    = [];

  constructor(
    private router:         Router,
    private route:          ActivatedRoute,
    private ciudadService:  CiudadService,
    private rutasService:   RutasService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    // — 1) Cargo la caravana para sincronizar vida y dinero globales
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: c => {
        this.vidaActual   = c.vidas;
        this.dineroActual = c.dinero;
      },
      error: err => console.error('Error cargando caravana:', err)
    });

    // — 2) Luego leo el parámetro ?origen=ID y cargo ciudad+adyacentes
    this.route.queryParams.subscribe(params => {
      const id = Number(params['origen']);
      if (!id) {
        console.error('No se recibió id de origen');
        return;
      }
      this.originCityId = id;

      // Ciudad actual
      this.ciudadService.obtenerCiudad(id).subscribe({
        next: ciudad => {
          this.actualCity = ciudad;
          this.loadAdjacent();
        },
        error: err => console.error('Error al cargar ciudad actual:', err)
      });
    });
  }

  private loadAdjacent() {
    this.adjacent = [];
    this.rutasService.obtenerRutas().subscribe({
      next: rutas => {
        rutas
          .filter(r => r.ciudadOrigenId === this.originCityId)
          .forEach(r => {
            // por cada ruta, pido la ciudad destino
            this.ciudadService.obtenerCiudad(r.ciudadDestinoId).subscribe({
              next: dest => {
                this.adjacent.push({
                  city: dest,
                  route: {
                    id:          r.id,
                    dano:        r.cantidad_dano,
                    descripcion: r.descripcion_dano
                  }
                });
              },
              error: err => console.error('Error al cargar destino:', err)
            });
          });
      },
      error: err => console.error('Error al cargar rutas:', err)
    });
  }

  travelTo(routeId: number) {
    console.log('Viajando por ruta', routeId);
    // lógica de viaje...
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }
}
