import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CaravanaService } from '../services-back/caravana.service';
import { StockCaravanaService } from '../services-back/stock-caravana.service';
import { Caravana } from '../Models/caravana';
import { StockCaravanaDTO } from '../Models/stock-caravana-dto';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-caravan',
  standalone: true,
  imports: [ CommonModule, RouterModule, PlayerUiComponent ],
  templateUrl: './caravan.component.html',
  styleUrls: ['./caravan.component.css']
})
export class CaravanComponent implements OnInit, OnDestroy {
  vidaActual = 0;
  dineroActual = 0;
  currentCaravana!: Caravana;
  productos: StockCaravanaDTO[] = [];
  private sub = new Subscription();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private caravanaSvc: CaravanaService,
    private stockCaravanaService: StockCaravanaService
  ) {}

  ngOnInit() {
    this.sub.add(
      this.route.queryParamMap.subscribe(q => {
        const id = +q.get('id')! || 1;
        this.caravanaSvc.obtenerCaravana(id).subscribe(c => {
          this.currentCaravana = c;
          this.vidaActual = c.vidas;
          this.dineroActual = c.dinero;
          // Cargar el stock real de la caravana
          this.cargarProductos(c.id);
        });
      })
    );
  }

  cargarProductos(caravanaId: number) {
    this.stockCaravanaService.obtenerStockPorCaravana(caravanaId).subscribe({
      next: (stock) => {
        this.productos = stock;
      },
      error: (err) => {
        console.error('Error al cargar inventario:', err);
      }
    });
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}