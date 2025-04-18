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
    if (this.productoSeleccionado) {
      this.productoSeleccionado = null; // Cierra el detalle
    } else {
      this.router.navigate(['/mapCaravania']); // Vuelve al menú si ya estaba en la lista
    }
  }
  btn2Click() {
    this.router.navigate(['/inventory']);

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
  
  productoSeleccionado: any = null;
  cantidadAComprar: number = 1;

consultar(servicio: any) {
  this.productoSeleccionado = { ...servicio, cantidad: 8 }; // o carga dinámica si tienes backend
  this.cantidadAComprar = 1;
}

calcularPrecio(producto: any): number {
  const valor = parseInt(producto.valor);
  return this.cantidadAComprar * (isNaN(valor) ? 0 : valor);
}

comprar(servicio: any) {
  console.log(`Comprando ${this.cantidadAComprar} de ${servicio.nombre}`);
}

  

}
