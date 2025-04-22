// src/app/map/map.component.ts
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CiudadService } from '../services-back/ciudad.service';
import { Ciudad } from '../Models/ciudad';
import { CaravanaService } from '../services-back/caravana.service';
declare var OpenSeadragon: any;

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit {

vidaActual! :number
dineroActual! :number

  actualCity: (Ciudad & { x: number; y: number }) | null = null;
  adjacentCities: Array<Ciudad & { x: number; y: number }> = [];

  constructor(
    private router: Router,
    private ciudadService: CiudadService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    this.ciudadService.obtenerCiudades().subscribe({
      next: (ciudades: Array<Ciudad & { x: number; y: number }>) => {
        if (ciudades.length > 0) {
          // 1) Tomamos la primera como actual
          this.actualCity = ciudades[0];
          // 2) El resto como adyacentes
          this.adjacentCities = ciudades.slice(1);
        }
      },
      error: err => console.error('No se pudieron cargar ciudades:', err)
    });

    this.caravanaService.obtenerCaravana(1).subscribe({
      next: (caravana) => {
        this.vidaActual = caravana.vidas;
        this.dineroActual = caravana.dinero;
      }
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
      // Overlay ciudad actual
      if (this.actualCity) {
        const el = document.getElementById('ciudad-actual')!;
        const coord = viewer.viewport.imageToViewportCoordinates(
          this.actualCity.x,
          this.actualCity.y
        );
        viewer.addOverlay({
          element: el,
          location: coord,
          placement: OpenSeadragon.Placement.CENTER
        });
      }
      // Overlays ciudades adyacentes
      this.adjacentCities.forEach((city, i) => {
        const el = document.getElementById(`ciudad-adyacente-${i}`)!;
        const coord = viewer.viewport.imageToViewportCoordinates(city.x, city.y);
        viewer.addOverlay({
          element: el,
          location: coord,
          placement: OpenSeadragon.Placement.CENTER
        });
      });
    });
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

}
