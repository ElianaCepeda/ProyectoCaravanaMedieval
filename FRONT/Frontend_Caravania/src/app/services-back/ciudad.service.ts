import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ciudad } from '../Models/ciudad';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  constructor(
    private http: HttpClient
  ) { }

  obtenerCiudades() {
    return this.http.get('http://localhost:8081/ciudad/list')
  }

  obtenerCiudad(id: number) {
    return this.http.get('http://localhost:8081/ciudad/' + id)
  }

  crearCiudad(ciudad: Ciudad) {
    return this.http.post('http://localhost:8081/ciudad/create', ciudad)
  }

  eliminarCiudad(id: number) {
    return this.http.delete('http://localhost:8081/ciudad/delete/' + id)
  }
}
