// src/app/caravan/caravan.component.ts
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PlayerUiComponent } from '../player-ui/player-ui.component';
import { CaravanaService } from '../services-back/caravana.service';
import { Caravana } from '../Models/caravana';
import { Subscription } from 'rxjs';

// Interface para los items del inventario
interface InventoryItem {
  id: number;
  name: string;
  quantity: number;
  description: string;
  icon: string;
}

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
  private sub = new Subscription();

  // Lista de objetos del inventario (datos quemados por ahora)
  inventoryItems: InventoryItem[] = [
    {
      id: 1,
      name: 'Espada de Hierro',
      quantity: 2,
      description: 'Una espada forjada con hierro de alta calidad. Aumenta el daño en combate.',
      icon: 'assets/img/items/sword.png'
    },
    {
      id: 2,
      name: 'Poción de Salud',
      quantity: 15,
      description: 'Restaura 50 puntos de vida cuando se consume.',
      icon: 'assets/img/items/health-potion.png'
    },
    {
      id: 3,
      name: 'Armadura de Cuero',
      quantity: 1,
      description: 'Armadura ligera que proporciona protección básica contra ataques.',
      icon: 'assets/img/items/leather-armor.png'
    },
    {
      id: 4,
      name: 'Comida Enlatada',
      quantity: 8,
      description: 'Alimento no perecedero que restaura energía durante los viajes.',
      icon: 'assets/img/items/canned-food.png'
    },
    {
      id: 5,
      name: 'Gemas Preciosas',
      quantity: 5,
      description: 'Gemas valiosas que pueden venderse por una buena cantidad de dinero.',
      icon: 'assets/img/items/gems.png'
    },
    {
      id: 6,
      name: 'Mapa Antiguo',
      quantity: 1,
      description: 'Un mapa que revela rutas secretas y ubicaciones de tesoros.',
      icon: 'assets/img/items/map.png'
    },
    {
      id: 7,
      name: 'Munición',
      quantity: 50,
      description: 'Balas para armas de fuego. Esenciales para la defensa de la caravana.',
      icon: 'assets/img/items/ammo.png'
    },
    {
      id: 8,
      name: 'Herramientas de Reparación',
      quantity: 3,
      description: 'Kit completo para reparar la caravana en caso de daños.',
      icon: 'assets/img/items/tools.png'
    },
    {
      id: 9,
      name: 'Antorcha',
      quantity: 12,
      description: 'Proporciona luz durante los viajes nocturnos.',
      icon: 'assets/img/items/torch.png'
    },
    {
      id: 10,
      name: 'Libro de Hechizos',
      quantity: 1,
      description: 'Contiene conocimiento mágico ancestral para conjuros poderosos.',
      icon: 'assets/img/items/spellbook.png'
    },
    {
      id: 11,
      name: 'Agua Purificada',
      quantity: 20,
      description: 'Agua limpia y segura para el consumo durante largos viajes.',
      icon: 'assets/img/items/water.png'
    },
    {
      id: 12,
      name: 'Cuerda Resistente',
      quantity: 2,
      description: 'Cuerda de alta resistencia útil para escalada y rescates.',
      icon: 'assets/img/items/rope.png'
    }
  ];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private caravanaSvc: CaravanaService
  ) {}

  ngOnInit() {
    this.sub.add(
      this.route.queryParamMap.subscribe(q => {
        const id = +q.get('id')! || 1;
        this.caravanaSvc.obtenerCaravana(id).subscribe(c => {
          this.currentCaravana = c;
          this.vidaActual = c.vidas;
          this.dineroActual = c.dinero;
        });
      })
    );
  }

  btnClick() {
    this.router.navigate(['/mapCaravania']);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}