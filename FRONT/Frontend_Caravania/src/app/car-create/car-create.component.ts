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

  caravanas = [
    {
      img: '../../assets/img/creation/velocidad.png',
      clase: 'Tormenta Rápida',
      stats: {
        salud: 80,
        velocidad: 100,
        capacidad: 50,
        dinero: 1000
      }
    },
    {
      img: '../../assets/img/creation/realeza.png',
      clase: 'Caravana Real',
      stats: {
        salud: 80,
        velocidad: 40,
        capacidad: 40,
        dinero: 6000
      }
    },
    {
      img: '../../assets/img/creation/capacidad.png',
      clase: 'Gigante de Carga',
      stats: {
        salud: 80,
        velocidad: 40,
        capacidad: 800,
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
