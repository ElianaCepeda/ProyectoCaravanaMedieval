import { Component } from '@angular/core';

@Component({
  selector: 'app-car-create',
  templateUrl: './car-create.component.html',
  styleUrls: ['./car-create.component.css']
})
export class CarCreateComponent {

  flechaImg = '../../assets/img/creation/flecha_.png';

  personajes = [
    {
      img: '../../assets/img/creation/velocidad.png',
      stats: ['Fuerza: 10', 'Agilidad: 5', 'Resistencia: 8']
    },
    {
      img: '../../assets/img/creation/realeza.png',
      stats: ['Fuerza: 6', 'Agilidad: 9', 'Resistencia: 5']
    },
    {
      img: '../../assets/img/creation/capacidad.png',
      stats: ['Fuerza: 6', 'Agilidad: 9', 'Resistencia: 5']
    }
  ];

  currentIndex = 0;

  get currentCharacter() {
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
