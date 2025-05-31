import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { Servicio } from '../Models/servicio';
import { CaravanaService } from '../services-back/caravana.service';
import { Caravana } from '../Models/caravana';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,        
    PlayerUiComponent
  ],
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {
  servicios: Servicio[] = [];
  vidaActual!: number;
  dineroActual!: number;

  caravana: Caravana = {
    id: 0,
    nombre: '',
    vidas: 0,
    dinero: 0,
    capacidad_actual: 0,
    velocidad_actual: 0,
    capacidad_maxima: 0,
    velocidad_maxima: 0,
    guardias: false
  };

  servicioSeleccionado: Servicio | null = null;

  constructor(
    private router: Router,
    private functionsService: FunctionsService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit() {
    // Cargar servicios disponibles
    this.functionsService.obtenerServicios(1).subscribe({
      next: (data: Servicio[]) => this.servicios = data,
      error: err => console.error('Error al obtener los servicios:', err)
    });

    // Cargar datos de la caravana
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: (caravana) => {
        this.caravana = caravana;
        this.vidaActual = caravana.vidas;
        this.dineroActual = caravana.dinero;
      },
      error: err => console.error('Error al obtener la caravana:', err)
    });
  }

  consultar(servicio: Servicio) {
    this.servicioSeleccionado = servicio;
  }

  comprar(servicio: Servicio) {
    const precio = servicio.precio;
  
    // 1) Validaciones específicas de cada servicio
    switch (servicio.nombre) {
      case 'Reparación de Caravana':
        if (this.vidaActual >= 100) {
          alert('Tu caravana ya está al máximo de salud.');
          return;
        }
        break;
  
      case 'Mejora de Capacidad':
        if (this.caravana.capacidad_actual >= this.caravana.capacidad_maxima) {
          alert('Ya has alcanzado la capacidad máxima.');
          return;
        }
        break;
  
      case 'Mejora de Velocidad':
        if (this.caravana.velocidad_actual >= this.caravana.velocidad_maxima) {
          alert('Ya has alcanzado la velocidad máxima.');
          return;
        }
        break;
  
      case 'Contratación de Guardias':
        if (this.caravana.guardias) {
          alert('Ya tienes guardias contratados.');
          return;
        }
        break;
  
      default:
        console.warn('Servicio desconocido:', servicio.nombre);
    }
  
    // 2) Validar que hay suficiente dinero
    if (this.dineroActual < precio) {
      alert('No tienes suficiente oro para comprar este servicio.');
      return;
    }
  
    // 3) Descontar el precio
    this.dineroActual -= precio;
    this.caravana.dinero = this.dineroActual;
  
    // 4) Aplicar el efecto del servicio
    switch (servicio.nombre) {
      case 'Reparación de Caravana':
        this.vidaActual = 100;
        this.caravana.vidas = 100;
        break;
  
      case 'Mejora de Capacidad':
        this.caravana.capacidad_actual = Math.min(
          this.caravana.capacidad_actual + 5,
          this.caravana.capacidad_maxima
        );
        break;
  
      case 'Mejora de Velocidad':
        this.caravana.velocidad_actual = Math.min(
          this.caravana.velocidad_actual + 5,
          this.caravana.velocidad_maxima
        );
        break;
  
      case 'Contratación de Guardias':
        this.caravana.guardias = true;
        break;
    }
  
    // 5) Cerrar la vista de detalle
    this.servicioSeleccionado = null;
  
    // 6) Persistir cambios en el backend
    this.caravanaService
      .actualizarCaravana(this.caravana.id, this.caravana)
      .subscribe({
        next: () => console.log('Caravana actualizada con éxito'),
        error: err => console.error('Error al actualizar la caravana:', err)
      });
  }
  
  btnClick() {
    // Regresar al mapa con la ciudad actual de la caravana
    if (this.caravana && this.caravana.ciudadActualId) {
      this.router.navigate(['/mapCaravania'], {
        queryParams: { actual: this.caravana.ciudadActualId }
      });
    } else {
      this.router.navigate(['/mapCaravania']);
    }
  }
}