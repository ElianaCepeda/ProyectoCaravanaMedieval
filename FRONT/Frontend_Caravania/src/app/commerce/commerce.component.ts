import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { FunctionsService } from '../services-back/functions.service';
import { CaravanaService } from '../services-back/caravana.service';
import { StockCaravanaService } from '../services-back/stock-caravana.service';

import { Producto } from '../Models/producto';
import { Caravana } from '../Models/caravana';
import { StockCaravanaDTO } from '../Models/stock-caravana-dto';

@Component({
  selector: 'app-commerce',
  standalone: true,
  imports: [CommonModule, PlayerUiComponent],
  templateUrl: './commerce.component.html',
  styleUrls: ['./commerce.component.css']
})
export class CommerceComponent implements OnInit {
  productos: Producto[] = [];

  vidaActual!: number;
  dineroActual!: number;
  caravana!: Caravana;

  productoSeleccionado: Producto | null = null;
  cantidadAComprar: number = 1;

  stockCaravana: StockCaravanaDTO[] = [];

  constructor(
    private router: Router,
    private functionsService: FunctionsService,
    private caravanaService: CaravanaService,
    private stockCaravanaService: StockCaravanaService
  ) {}

  ngOnInit(): void {
    // Obtener productos desde el backend
    this.functionsService.obtenerProductos().subscribe(
      (data: Producto[]) => {
        this.productos = data;
      },
      (error: any) => {
        console.error('Error al obtener los productos:', error);
      }
    );

    // Cargar la caravana y luego su stock
    this.caravanaService.obtenerCaravana(1).subscribe(
      (c: Caravana) => {
        this.caravana = c;
        this.vidaActual = c.vidas;
        this.dineroActual = c.dinero;
        this.cargarStock();
      },
      (err: any) => {
        console.error('Error cargando caravana:', err);
      }
    );
  }

  /** Obtiene el stock desde el backend y lo guarda en this.stockCaravana */
  private cargarStock(): void {
    this.stockCaravanaService.obtenerStockPorCaravana(this.caravana.id).subscribe(
      (stockArr: StockCaravanaDTO[]) => {
        this.stockCaravana = stockArr;
      },
      (err: any) => {
        console.error('Error al cargar stock de caravana:', err);
      }
    );
  }

  // Volver al mapa
  btnClick(): void {
    this.router.navigate(['/mapCaravania']);
  }

  // Ir al inventario (pantalla de vender)
  btn2Click(): void {
    this.router.navigate(['/inventory']);
  }

  // Al hacer clic en "Consultar" en la lista de productos
  consultar(producto: Producto): void {
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

  /** 
   * Método que efectúa la compra y luego refresca el stock.
   * Resta dinero, actualiza la caravana en el backend y luego
   * invoca a actualizarStock() para crear ó aumentar la fila de stock.
   */
  comprar(producto: Producto): void {
    let cantidad = 1;
    if (this.productoSeleccionado && this.productoSeleccionado.id === producto.id) {
      cantidad = this.cantidadAComprar;
    }

    // Sumar la cantidad total de objetos en el inventario
  const totalActual = this.stockCaravana.reduce((acc, item) => acc + item.cantidad, 0);

  // Validar capacidad máxima
  if (totalActual + cantidad > this.caravana.capacidad_maxima) {
    alert('¡No puedes exceder la capacidad máxima de la caravana!');
    return;
  }

  const precioUnitario = Number(producto.precio);
  const precioTotal = cantidad * precioUnitario;
  if (this.dineroActual < precioTotal) {
    console.warn('No tienes suficiente dinero para esta compra.');
    return;
  }

  

    // Descontar dinero en memoria
    this.dineroActual -= precioTotal;
    this.caravana.dinero = this.dineroActual;

    // Actualizar la caravana en el backend (solo vida y dinero)
    const { ciudadActualId, ...caravanaParaEnviar } = this.caravana;
    this.caravanaService.actualizarCaravana(this.caravana.id, caravanaParaEnviar).subscribe(
      () => {
        // Una vez actualizada la caravana, actualizar el stock
        this.actualizarStock(producto.id, cantidad);
      },
      (err: any) => {
        console.error('Error al actualizar caravana en backend:', err);
      }
    );

    // Si estaba mostrando el detalle, cerrarlo
    if (this.productoSeleccionado) {
      this.productoSeleccionado = null;
      this.cantidadAComprar = 1;
    }
  }

  /**
   * Crea o actualiza (incrementa) una fila en stock_caravana luego
   * de haber comprado. Si ya existe (productoId coincide), incrementa;
   * si no existe, crea una nueva fila.
   */
  private actualizarStock(productoId: number, cantidad: number): void {
    const entradaExistente = this.stockCaravana.find(
      (sc: StockCaravanaDTO) => sc.productoId === productoId
    );

    if (entradaExistente) {
      // Si ya hay registro, llamamos al endpoint comprarStockCaravana()
      this.stockCaravanaService.comprarStockCaravana(entradaExistente.id!, cantidad).subscribe(
        () => {
          this.cargarStock();
        },
        (err: any) => {
          console.error('Error al aumentar cantidad en stock:', err);
        }
      );
    } else {
      // Si NO existe, llamamos a crearStockCaravana()
      this.stockCaravanaService.crearStockCaravana(this.caravana.id, productoId, cantidad).subscribe(
        () => {
          this.cargarStock();
        },
        (err: any) => {
          console.error('Error al crear nueva entrada de stock:', err);
        }
      );
    }
  }
}