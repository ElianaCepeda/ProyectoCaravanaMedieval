import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-welcome-page',
  imports: [],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css'
})
export class WelcomePageComponent {

  constructor(private router: Router) {}

  btnClickNuevo() {
    this.router.navigateByUrl('/caravan-create');
  };
  
  menuOptions: string[] = ['Nuevo juego', 'Cerrar'];

  seleccionarOpcion(opcion: string): void {
    console.log('Seleccionaste:', opcion);
  }
  
}
