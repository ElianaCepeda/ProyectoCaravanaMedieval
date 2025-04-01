import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigateByUrl('/caravan-create');
    }, 2000); 
  }
  



}
