import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RutasService {

  constructor(
    private http: HttpClient
  ) { }

  obtenerRutas() {
    return this.http.get('http://localhost:8081/ruta/list')
  }

  obtenerRuta(id: number) {
    return this.http.get('http://localhost:8081/ruta/' + id)
  }
}
