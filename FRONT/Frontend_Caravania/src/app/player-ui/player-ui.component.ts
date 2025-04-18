import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-player-ui',
  standalone: true,
  imports: [CommonModule], 
  templateUrl: './player-ui.component.html',
  styleUrls: ['./player-ui.component.css']
})
export class PlayerUiComponent {
  @Input() vida: number = 100;
  @Input() dinero: number = 9999;
  @Input() mostrarVida: boolean = true;
  @Input() mostrarDinero: boolean = true;
  dineroImg: string = '../../assets/img/global/dinero.png';
  corazonImg: string = '../../assets/img/global/corazon.png';
}
