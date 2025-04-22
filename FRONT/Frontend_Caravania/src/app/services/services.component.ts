import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { Servicio } from '../Models/servicio';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule, Router, PlayerUiComponent],
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {
  servicios: Servicio[] = [];
  vidaActual = 100;
  dineroActual = 9999;

  // Para mostrar detalle de un servicio
  servicioSeleccionado: Servicio | null = null;

  constructor(
    private router: Router,
    private functionsService: FunctionsService
  ) {}

  ngOnInit() {
    this.functionsService.obtenerServicios(1).subscribe({
      next: (data: Servicio[]) => {
        this.servicios = data;
      },
      error: err => console.error('Error al obtener los servicios:', err)
    });
  }

  consultar(servicio: Servicio) {
    this.servicioSeleccionado = servicio;
  }

  comprar(servicio: Servicio) {
    const precio = servicio.precio;
    if (this.dineroActual >= precio) {
      // 1) Descontar el precio del oro
      this.dineroActual -= precio;

      // 2) (Opcional) llamar al backend para procesar la compra
      // this.functionsService.comprarServicio(servicio.id).subscribe();

      // 3) Cerrar detalle si estaba abierto
      this.servicioSeleccionado = null;
      console.log(`Comprado "${servicio.nombre}" por ${precio} monedas.`);
    } else {
      alert('No tienes suficiente oro para comprar este servicio.');
    }
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }
}
