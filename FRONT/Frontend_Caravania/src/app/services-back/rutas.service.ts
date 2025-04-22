import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ciudad } from '../Models/ciudad';

export interface Ruta {
  id: number;
  dano: number;
  distancia: number;
  descripcion_dano: string;
  ciudadOrigen: Ciudad;
  ciudadDestino: Ciudad;
}

@Injectable({
  providedIn: 'root'
})
export class RutasService {
  private readonly baseUrl = 'http://localhost:8081/ruta';

  constructor(private http: HttpClient) {}

  obtenerRutas(): Observable<Ruta[]> {
    return this.http.get<Ruta[]>(`${this.baseUrl}/list`);
  }

  obtenerRuta(id: number): Observable<Ruta> {
    return this.http.get<Ruta>(`${this.baseUrl}/${id}`);
  }
}
