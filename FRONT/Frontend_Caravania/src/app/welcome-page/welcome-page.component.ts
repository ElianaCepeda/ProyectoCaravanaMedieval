import { Component } from '@angular/core';

@Component({
  selector: 'app-welcome-page',
  imports: [],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css'
})
export class WelcomePageComponent {
  
  menuOptions: string[] = ['Nuevo juego', 'Cerrar'];

  seleccionarOpcion(opcion: string): void {
    console.log('Seleccionaste:', opcion);
  }
  
}
