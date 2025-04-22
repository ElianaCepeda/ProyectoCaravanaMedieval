import { Component, AfterViewInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
declare var OpenSeadragon: any;

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {

  cityName = 'Ciudad Quemada';
  
  adjacentCities = [
    { name: 'Ciudad A', x: 16000, y: 7200 },
    { name: 'Ciudad B', x: 16200, y: 7100 },
    // …más ciudades
  ];
  
  constructor(private router: Router) {}

  
  vidaActual = 100;
  dineroActual = 9999;


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
        dblClickToZoom: true,
        scrollToZoom: true,
        pinchToZoom: true
      },
      
    });

    viewer.addHandler('open', () => {
      const actualEl = document.getElementById('ciudad-actual')!;
      const coord0 = viewer.viewport.imageToViewportCoordinates(16500, 7000);
      viewer.addOverlay({ element: actualEl, location: coord0, placement: OpenSeadragon.Placement.CENTER });

      // 2) Ciudad(es) adyacente(s)
      this.adjacentCities.forEach((city, i) => {
        const el = document.getElementById(`ciudad-adyacente-${i}`)!;
        const coords = viewer.viewport.imageToViewportCoordinates(city.x, city.y);
        viewer.addOverlay({ element: el, location: coords, placement: OpenSeadragon.Placement.CENTER });
      });
    });
  }
  goCommerce() {
    this.router.navigate(['/commerce']);
  }
  goService() {
    this.router.navigate(['/services']);
  }
  goTravel() {
    this.router.navigate(['/role']);
  }
}
