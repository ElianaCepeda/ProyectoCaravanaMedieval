import { Component, AfterViewInit } from '@angular/core';
declare var OpenSeadragon: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {

  dineroImg = '../../assets/img/map/dinero.png';
  barraImg = '../../assets/img/map/barra-salud.png';
  corazonImg = '../../assets/img/map/corazon.png';

  vidaActual = 100;

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
      blendTime: 0.15,
      imageSmoothingEnabled: true,    // suaviza tiles al escalar
      maxImageCacheCount: 200
    });

    // Cuando se abra el tile source, agregamos el overlay utilizando el div ya existente
    viewer.addHandler('open', () => {
      const markerEl = document.getElementById("ciudad-marcador");

      if (markerEl) {

        markerEl.setAttribute('data-name', 'Ciudad Quemada');
        // Coordenadas en la imagen original (ajusta estos valores según necesites)
        const coords = viewer.viewport.imageToViewportCoordinates(14500, 7000);
        console.log("Coordenadas del marcador:", coords);
        viewer.addOverlay({
          element: markerEl,
          location: coords,
          placement: OpenSeadragon.Placement.CENTER
        });
      } else {
        console.error("No se encontró el elemento con id 'ciudad-marcador'.");
      }
    });
  }
}
