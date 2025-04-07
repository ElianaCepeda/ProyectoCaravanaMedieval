import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-role-select',
  imports: [],
  templateUrl: './role-select.component.html',
  styleUrl: './role-select.component.css'
})
export class RoleSelectComponent {

  constructor(private router: Router) {}

  flechaImg = '../../assets/img/creation/flecha_.png';

  btnClick() {
    this.router.navigate(['/caravan-create']);

  };

  personajes = [
    {
      img: '../../assets/img/rol/comerciante.png',
      clase: 'Comerciante',
      stats: 'Maestro del trueque en las plazas de las ciudades. Sus productos gozan de un precio reducido, mas sus pies no pisan caminos lejanos ni paga servicios en tierras extrañas.'
    },
    {
      img: '../../assets/img/rol/caravanero.png',
      clase: 'Caravanero',
      stats: 'Viajero de largas rutas y señor de su caravana. Puede cruzar ciudades, pagar tributos y comerciar, aunque sin el favor del descuento que gozan los mercaderes establecidos.'
    }
  ];
  
  

  currentIndex = 0;

  get currentPersonaje() {
    return this.personajes[this.currentIndex];
  }

  anterior(): void {
    this.currentIndex =
      (this.currentIndex - 1 + this.personajes.length) % this.personajes.length;
  }

  siguiente(): void {
    this.currentIndex = (this.currentIndex + 1) % this.personajes.length;
  }

}
