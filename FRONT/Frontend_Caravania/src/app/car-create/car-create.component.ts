import { Component } from '@angular/core';

@Component({
  selector: 'app-car-create',
  templateUrl: './car-create.component.html',
  styleUrls: ['./car-create.component.css']
})
export class CarCreateComponent {

  flechaImg = '../../assets/img/creation/flecha_.png';

  caravanas = [
    {
      img: '../../assets/img/creation/velocidad.png',
      clase: 'Tormenta Rápida',
      stats: {
        salud: 80,
        velocidad: 10,
        capacidad: 50,
        dinero: 200
      }
    },
    {
      img: '../../assets/img/creation/realeza.png',
      clase: 'Caravana Real',
      stats: {
        salud: 70,
        velocidad: 6,
        capacidad: 40,
        dinero: 400
      }
    },
    {
      img: '../../assets/img/creation/capacidad.png',
      clase: 'Gigante de Carga',
      stats: {
        salud: 90,
        velocidad: 4,
        capacidad: 80,
        dinero: 150
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
