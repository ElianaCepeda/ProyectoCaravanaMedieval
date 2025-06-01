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
  caravanaId = 1; // Cambia esto si tienes un sistema de usuario/caravana dinámica

  constructor(
    private router: Router,
    private stockCaravanaService: StockCaravanaService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    // Obtener la caravana actual y actualizar vida/dinero
    this.caravanaService.obtenerCaravana(this.caravanaId).subscribe({
      next: (caravana: Caravana) => {
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
      },
      error: (err) => {
        console.error('Error al cargar productos comprados:', err);
      }
    });
  }

  btnClick() {
    this.router.navigate(['/commerce']);
  }
}