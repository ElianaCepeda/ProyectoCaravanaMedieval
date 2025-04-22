// src/app/travel/travel.component.ts
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { RutasService, Ruta } from '../services-back/rutas.service';
import { FunctionsService } from '../services-back/functions.service';
import { CaravanaService } from '../services-back/caravana.service';
import { Ciudad } from '../Models/ciudad';
import { Servicio } from '../Models/servicio';
import { Caravana } from '../Models/caravana';

interface Adjacent {
  city: Ciudad;
  route: Ruta;
}

@Component({
  selector: 'app-travel',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    PlayerUiComponent
  ],
  templateUrl: './travel.component.html',
  styleUrls: ['./travel.component.css']
})
export class TravelComponent implements OnInit {
  vidaActual   = 0;
  dineroActual = 0;

  originCityId!: number;
  actualCity: Ciudad | null = null;
  adjacent: Adjacent[] = [];

  servicios: Servicio[] = [];
  caravana!: Caravana;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ciudadService: CiudadService,
    private rutasService: RutasService,
    private functionsService: FunctionsService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    // — Primero cargamos el estado de la caravana (vidas y dinero)
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: c => {
        this.caravana    = c;
        this.vidaActual   = c.vidas;
        this.dineroActual = c.dinero;
      },
      error: err => console.error('Error al cargar caravana:', err)
    });

    // — Luego los servicios disponibles
    this.functionsService.obtenerServicios(1).subscribe({
      next: s => this.servicios = s,
      error: err => console.error('Error al cargar servicios:', err)
    });

    // — Finalmente leemos el parámetro origen y cargamos ciudad + adyacentes
    this.route.queryParams.subscribe(params => {
      const id = Number(params['origen']);
      if (!id) {
        console.error('No se recibió id de ciudad de origen');
        return;
      }
      this.originCityId = id;

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
          .filter(r => r.ciudadOrigen.id === this.originCityId)
          .forEach(r => {
            this.ciudadService.obtenerCiudad(r.ciudadDestino.id).subscribe({
              next: destino => this.adjacent.push({ city: destino, route: r }),
              error: err => console.error('Error al cargar ciudad destino:', err)
            });
          });
      },
      error: err => console.error('Error al cargar rutas:', err)
    });
  }

  travelTo(routeId: number) {
    console.log('Viajando por ruta', routeId);
    // this.router.navigate(['/caravana/viajar'], { queryParams: { ruta: routeId } });
  }

  commerceTo(cityId: number) {
    console.log('Comerciar en ciudad', cityId);
    this.router.navigate(['/commerce'], { queryParams: { ciudad: cityId } });
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }
}
