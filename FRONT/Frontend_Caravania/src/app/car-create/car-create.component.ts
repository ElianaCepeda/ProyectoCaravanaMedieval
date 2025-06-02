import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-create',
  templateUrl: './car-create.component.html',
  styleUrls: ['./car-create.component.css']
})


export class CarCreateComponent {
  
  constructor(private router: Router) {}

  flechaImg = '../../assets/img/creation/flecha_.png';

  btnClick() {
    this.router.navigate(['/loading'], { queryParams: { next: '/welcome' } });
  };

  btnClick2() {
    this.router.navigate(['/role']);

  };

  caravanas = [
    {
      img: '../../assets/img/creation/velocidad.png',
      clase: 'Tormenta Rápida',
      stats: {
        salud: 100,
        velocidad: 2,
        capacidad: 2,
        dinero: 1250
      }
    },
    {
      img: '../../assets/img/creation/realeza.png',
      clase: 'Caravana Real',
      stats: {
        salud: 100,
        velocidad: 1,
        capacidad: 1,
        dinero: 3500
      }
    },
    {
      img: '../../assets/img/creation/capacidad.png',
      clase: 'Gigante de Carga',
      stats: {
        salud: 100,
        velocidad: 1,
        capacidad: 3,
        dinero: 1000
      }
    }
  ];
  
  

  currentIndex = 0;

  get currentCaravana() {
    return this.caravanas[this.currentIndex];
  }

  anterior(): void {
    this.currentIndex =
      (this.currentIndex - 1 + this.caravanas.length) % this.caravanas.length;
  }

  siguiente(): void {
    this.currentIndex = (this.currentIndex + 1) % this.caravanas.length;
  }
}
