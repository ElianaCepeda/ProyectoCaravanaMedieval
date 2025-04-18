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
  constructor(private router: Router) {}

  
  dineroImg  = '../../assets/img/global/dinero.png';
  corazonImg = '../../assets/img/global/corazon.png';
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
      const markerEl = document.getElementById("ciudad-marcador");
      if (!markerEl) {
        console.error("No se encontró el marcador.");
        return;
      } 
      
      const coords = viewer.viewport.imageToViewportCoordinates(16500, 7000);      

      viewer.addOverlay({
        element: markerEl,
        location: coords,
        placement: OpenSeadragon.Placement.CENTER
      });

      
    });
  }
  goWelcome() {
    this.router.navigate(['/welcome']);
  }
  goCreate() {
    this.router.navigate(['/caravan-create']);
  }
  goRole() {
    this.router.navigate(['/role']);
  }
}
