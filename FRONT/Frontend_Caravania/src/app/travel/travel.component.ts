import { Component, AfterViewInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { Ciudad } from '../Models/ciudad';

@Component({
  selector: 'app-travel',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './travel.component.html',
  styleUrl: './travel.component.css'
})
export class TravelComponent {

  vidaActual = 100;
  dineroActual = 9999;

  // Lista dinámica de ciudades adyacentes
  adjacentCities = [
    { name: 'Ciudad A' },
    { name: 'Ciudad B' },
    { name: 'Ciudad C' },
    // …puedes añadir más
  ];

  constructor(private router: Router) {}

  // Maneja click en “Viajar”
  travelTo(city: { name: string }) {
    console.log('Viajando a', city.name);
    // this.router.navigate(['/destino'], { queryParams: { ciudad: city.name } });
  }

  // Maneja click en “Comerciar”
  commerceTo(city: { name: string }) {
    console.log('Comerciar en', city.name);
    // this.router.navigate(['/commerce'], { queryParams: { ciudad: city.name } });
  }

  // Botón de regresar
  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }

}
