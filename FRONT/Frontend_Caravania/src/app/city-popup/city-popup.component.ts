import { Component } from '@angular/core';

@Component({
  selector: 'app-city-popup',
  templateUrl: './city-popup.component.html',
  styleUrls: ['./city-popup.component.css']  // nota: es styleUrls, no styleUrl
})
export class CityPopupComponent {
  showPopup: boolean = true;

  ciudad: string = 'Shaelhan';
  descripcion: string = 'Una ciudad ancestral rodeada de bruma y misterio.';

  cerrarPopup(): void {
    this.showPopup = false;
  }
}
