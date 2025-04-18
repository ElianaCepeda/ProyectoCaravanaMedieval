import { Component, AfterViewInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';

@Component({
  selector: 'app-commerce',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './commerce.component.html',
  styleUrl: './commerce.component.css'
})
export class CommerceComponent {

  constructor(private router: Router) {}

  vidaActual = 100;
  dineroActual = 9999;

  btnClick() {
    this.router.navigate(['/mapCaravania']);

  };

  servicios = [
    { nombre: 'Reparar', valor: '20 monedas' },
    { nombre: 'Mejorar capacidad', valor: '20 monedas' },
    { nombre: 'Mejorar velocidad', valor: '20 monedas' },
    { nombre: 'Guardias', valor: '20 monedas' },
    { nombre: 'Mejorar vida', valor: '20 monedas' },
    { nombre: 'Mejorar defensa', valor: '20 monedas' },
    { nombre: 'Mejorar ataque', valor: '20 monedas' },
    { nombre: 'Mejorar suerte', valor: '20 monedas' },
    { nombre: 'Mejorar magia', valor: '20 monedas' }
  ];
  
  consultar(servicio: any) {
    console.log('Consultar:', servicio.nombre);
  }
  
  comprar(servicio: any) {
    console.log('Comprar:', servicio.nombre);
  }
  

}
