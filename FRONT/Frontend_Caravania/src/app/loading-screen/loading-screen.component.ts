import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loading-screen',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './loading-screen.component.html',
  styleUrls: ['./loading-screen.component.css']
})
export class LoadingScreenComponent implements OnInit {

  currenLoad = '../../assets/img/loading/barra-carga.png';

  backgroundImage: string = '';

  imagenes = [
    { img: '../../assets/img/loading/fondo1.png' },
    { img: '../../assets/img/loading/fondo2.png' },
    { img: '../../assets/img/loading/fondo3.png' },
    { img: '../../assets/img/loading/fondo4.png' }
  ];

  nextRoute: string = '';

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {

    const randomImg = this.imagenes[Math.floor(Math.random() * this.imagenes.length)].img;

    // Construye el background completo (gradiente + imagen aleatoria)
    this.backgroundImage = `
    linear-gradient(rgba(0, 0, 0, 0.45), rgba(0, 0, 0, 0.45)),
    url('${randomImg}')
  `;



    this.nextRoute = this.route.snapshot.queryParamMap.get('next') || '/welcome';

    setTimeout(() => {
      this.router.navigateByUrl(this.nextRoute);
    }, 3500); // redirige después de 3.5s
  }
}
