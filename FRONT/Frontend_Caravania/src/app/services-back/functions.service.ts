import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FunctionsService {

  constructor(
    private http: HttpClient
  ) { }


  

  obtenerServicios(ciudadId: number) {
    return this.http.get('http://localhost:8081/game/servicios');
  }

  obtenerProductos(){
    return this.http.get('http://localhost:8081/game/productos');
  }
}
