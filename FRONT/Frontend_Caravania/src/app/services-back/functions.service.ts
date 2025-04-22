import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Servicio } from '../Models/servicio';
import { Producto } from '../Models/producto';

@Injectable({
  providedIn: 'root'
})
export class FunctionsService {
  private readonly baseUrl = 'http://localhost:8081/game';

  constructor(private http: HttpClient) {}

  /** Devuelve la lista de servicios para una ciudad */
  obtenerServicios(ciudadId: number): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.baseUrl}/servicios?ciudadId=${ciudadId}`);
  }

  /** Devuelve la lista de productos */
  obtenerProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.baseUrl}/productos`);
  }

  
}
