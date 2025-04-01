import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-loading-screen',
  imports: [],
  templateUrl: './loading-screen.component.html',
  styleUrl: './loading-screen.component.css'
})
export class LoadingScreenComponent {

  currenLoad= '../../assets/img/loading/barra-carga.png';

  imagenes = [
    {
      img: '../../assets/img/loading/barra-carga.png'
    },
    {
      img: '../../assets/img/loading/barra-carga.png'
    },
    {
      img: '../../assets/img/loading/barra-carga.png'
    }
  ];

  nextRoute: string = '';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.nextRoute = this.route.snapshot.queryParamMap.get('next') || '/welcome';
    
    setTimeout(() => {
      this.router.navigateByUrl(this.nextRoute);
    }, 3500);
  }



}
