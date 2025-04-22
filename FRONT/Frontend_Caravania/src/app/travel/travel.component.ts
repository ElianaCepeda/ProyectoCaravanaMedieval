import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, RouterModule } from '@angular/router'; // ← RouterModule
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { RutasService, Ruta } from '../services-back/rutas.service';
import { Ciudad } from '../Models/ciudad';

interface Adjacent {
  city: Ciudad;
  route: Ruta;
}

@Component({
  selector: 'app-travel',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,        // ← en lugar de Router o ActivatedRoute
    PlayerUiComponent
  ],
  templateUrl: './travel.component.html',
  styleUrls: ['./travel.component.css']
})
export class TravelComponent implements OnInit {
  vidaActual = 100;
  dineroActual = 9999;

  originCityId!: number;
  actualCity: Ciudad | null = null;
  adjacent: Adjacent[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ciudadService: CiudadService,
    private rutasService: RutasService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const id = +params['origen'];
      if (!id) {
        console.error('No se recibió id de ciudad de origen');
        return;
      }
      this.originCityId = id;

      // 1) Carga la ciudad actual
      this.ciudadService.obtenerCiudad(id).subscribe({
        next: ciudad => {
          this.actualCity = ciudad;
          // 2) Carga rutas y filtra adyacentes
          this.loadAdjacent();
        },
        error: () => console.error('No se pudo cargar ciudad actual')
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
            this.ciudadService.obtenerCiudad(r.ciudadDestino.id)
              .subscribe(ciudadDestino => {
                this.adjacent.push({ city: ciudadDestino, route: r });
              });
          });
      },
      error: () => console.error('No se pudieron cargar rutas')
    });
  }

  travelTo(routeId: number) {
    console.log('Viajando por ruta', routeId);
  }

  commerceTo(cityId: number) {
    console.log('Comerciar en ciudad', cityId);
    this.router.navigate(['/commerce'], { queryParams: { ciudad: cityId } });
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }
}
