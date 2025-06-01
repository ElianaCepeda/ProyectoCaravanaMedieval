// src/app/commerce/commerce.component.ts
import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { CaravanaService } from '../services-back/caravana.service';
import { Producto } from '../Models/producto';
import { Caravana } from '../Models/caravana';

@Component({
  selector: 'app-commerce',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './commerce.component.html',
  styleUrls: ['./commerce.component.css']
})
export class CommerceComponent implements OnInit {
  productos: Producto[] = [];

  vidaActual!: number;
  dineroActual!: number;
  caravana!: Caravana; // Para almacenar la caravana completa

  // Para manejar el detalle de un producto
  productoSeleccionado: Producto | null = null;
  cantidadAComprar: number = 1;

  constructor(
    private router: Router,
    private functionsService: FunctionsService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit() {
    // 1) Obtener productos desde el backend
    this.functionsService.obtenerProductos().subscribe({
      next: (data: Producto[]) => {
        this.productos = data;
      },
      error: error => {
        console.error('Error al obtener los productos:', error);
      }
    });

    // 2) Cargar la caravana para mostrar vida y dinero
    this.caravanaService.obtenerCaravana(1).subscribe({
      next: c => {
        this.caravana = c;
        this.vidaActual = c.vidas;
        this.dineroActual = c.dinero;
      },
      error: err => console.error('Error cargando caravana:', err)
    });
  }

  // Volver al mapa
  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }

  // Ir al inventario (pantalla de vender)
  btn2Click() {
    this.router.navigate(['/inventory']);
  }

  // Cuando el usuario hace clic en "Consultar" en la lista de productos
  consultar(producto: Producto) {
    // Clonamos el objeto para no modificar directamente el de la lista
    this.productoSeleccionado = { ...producto };
    this.cantidadAComprar = 1;
  }

  // Calcula el precio total según la cantidad seleccionada
  calcularPrecio(producto: Producto): number {
    const precioUnitario = Number(producto.precio);
    if (isNaN(precioUnitario) || precioUnitario < 0) {
      return 0;
    }
    return this.cantidadAComprar * precioUnitario;
  }

  // Método que efectúa la compra (sin alertas ni toasts)
  comprar(producto: Producto) {
    // Determinamos cuántas unidades comprar:
    let cantidad = 1;
    if (this.productoSeleccionado && this.productoSeleccionado.id === producto.id) {
      // Si estamos en el detalle, compramos la cantidad indicada
      cantidad = this.cantidadAComprar;
    }

    // Calcular precio total
    const precioUnitario = Number(producto.precio);
    const precioTotal = cantidad * precioUnitario;

    // Verificar que la caravana tenga suficiente dinero
    if (this.dineroActual < precioTotal) {
      // No hay suficiente dinero: solo escribimos en consola y salimos
      console.warn('No tienes suficiente dinero para esta compra.');
      return;
    }

    // 1) Descontar el dinero en memoria
    this.dineroActual -= precioTotal;
    this.caravana.dinero = this.dineroActual;

    // 2) Actualizar la caravana en el backend, excluyendo campos extra (p.ej. ciudadActualId)
    const { ciudadActualId, ...caravanaParaEnviar } = this.caravana;
    this.caravanaService
      .actualizarCaravana(this.caravana.id, caravanaParaEnviar)
      .subscribe({
        next: () => {
          // Compra exitosa: si estábamos en el detalle, cerramos el detalle
          if (this.productoSeleccionado) {
            this.productoSeleccionado = null;
            this.cantidadAComprar = 1;
          }
          // Ya no mostramos alerta; el dinero en pantalla ya refleja el cambio
        },
        error: err => {
          // Error al actualizar en backend: solo escribir en consola
          console.error('Error al actualizar caravana en backend:', err);
        }
      });
  }
}
