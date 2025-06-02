import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { StockCaravanaService } from '../services-back/stock-caravana.service';
import { StockCaravanaDTO } from '../Models/stock-caravana-dto';
import { CaravanaService } from '../services-back/caravana.service';
import { Caravana } from '../Models/caravana';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, RouterModule, PlayerUiComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  vidaActual = 0;
  dineroActual = 0;
  productos: StockCaravanaDTO[] = [];
  caravanaId = 1;
  cantidadAVender: { [stockId: number]: number } = {};
  ultimoPremio: { [stockId: number]: number } = {};
  caravana!: Caravana;

  constructor(
    private router: Router,
    private stockCaravanaService: StockCaravanaService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    // Obtener la caravana actual y actualizar vida/dinero
    this.caravanaService.obtenerCaravana(this.caravanaId).subscribe({
      next: (caravana: Caravana) => {
        this.caravana = caravana;
        this.vidaActual = caravana.vidas;
        this.dineroActual = caravana.dinero;
        this.cargarProductos(caravana.id);
      },
      error: (err) => {
        console.error('Error al cargar caravana:', err);
      }
    });
  }

  cargarProductos(caravanaId: number) {
    this.stockCaravanaService.obtenerStockPorCaravana(caravanaId).subscribe({
      next: (stock) => {
        this.productos = stock;
        // Inicializa la cantidad a vender y el último premio para cada producto
        this.productos.forEach(item => {
          this.cantidadAVender[item.id!] = 1;
          this.ultimoPremio[item.id!] = 0;
        });
      },
      error: (err) => {
        console.error('Error al cargar productos comprados:', err);
      }
    });
  }

  btnClick() {
    this.router.navigate(['/commerce']);
  }

  // Ruleta: premio aleatorio entre 0 y 100 por unidad
  calcularPremio(): number {
  return Math.floor(Math.random() * 201) - 100; // -100 a 100
}

  venderProducto(item: StockCaravanaDTO) {
    const cantidad = this.cantidadAVender[item.id!] || 1;
    if (cantidad < 1 || cantidad > item.cantidad) {
      alert('Cantidad inválida');
      return;
    }

    // Calcula el premio total como suma de ruleta por cada unidad
    let premioTotal = 0;
    for (let i = 0; i < cantidad; i++) {
      premioTotal += this.calcularPremio();
    }
    this.ultimoPremio[item.id!] = premioTotal;

    // Resta la cantidad vendida localmente
    item.cantidad -= cantidad;

    // Suma el dinero a la bolsa local
    this.dineroActual += premioTotal;
    this.caravana.dinero = this.dineroActual;

    // Actualiza el dinero globalmente en el backend
    this.caravanaService.actualizarCaravana(this.caravanaId, this.caravana).subscribe({
      next: () => {
        // Ahora actualiza el stock en el backend
        if (item.cantidad === 0) {
          // Elimina el producto del backend
          this.stockCaravanaService.eliminarStockCaravana(item.id!).subscribe({
            next: () => this.cargarProductos(this.caravanaId)
          });
        } else {
          // Actualiza la cantidad en el backend
          this.stockCaravanaService.actualizarCantidadStockCaravana(item.id!, item.cantidad).subscribe({
            next: () => this.cargarProductos(this.caravanaId)
          });
        }
      },
      error: (err) => {
        console.error('Error al actualizar dinero global:', err);
      }
    });
  }

  actualizarCantidad(itemId: number, event: any) {
    const valor = parseInt(event.target.value) || 1;
    // Asegurarse de que el valor esté dentro del rango válido
    const producto = this.productos.find(p => p.id === itemId);
    if (producto) {
      this.cantidadAVender[itemId] = Math.max(1, Math.min(valor, producto.cantidad));
    }
  }
}