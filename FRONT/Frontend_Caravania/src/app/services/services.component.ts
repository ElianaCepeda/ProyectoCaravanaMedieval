import { Component, AfterViewInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { Servicio } from '../Models/servicio';


@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './services.component.html',
  styleUrls:   ['./services.component.css']
})
export class ServicesComponent {

  servicios: Servicio[] = [];
  
  constructor(private router: Router,
    private functionsService: FunctionsService
  ) {}

  ngOnInit() {
    this.functionsService.obtenerServicios(1).subscribe((data: any) => {
      console.log(data);
      this.servicios = data;
    }, (error) => {
      console.error('Error al obtener los servicios:', error);
    });
  }


  vidaActual = 100;
  dineroActual = 9999;

  btnClick() {
    if (this.productoSeleccionado) {
      this.productoSeleccionado = null; // Cierra el detalle
    } else {
      this.router.navigate(['/mapCaravania']); // Vuelve al menú si ya estaba en la lista
    }
  }

 
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
