// src/app/services-back/rutas.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface RutaDTO {
  id:               number;
  cantidad_dano:    number;
  descripcion_dano: string;
  ciudadOrigenId:   number;
  ciudadDestinoId:  number;
  ciudadOrigenNombre:  string;
  ciudadDestinoNombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class RutasService {
  private readonly baseUrl = 'http://localhost:8081/ruta';
  constructor(private http: HttpClient) {}

  obtenerRutas(): Observable<RutaDTO[]> {
    return this.http.get<RutaDTO[]>(`${this.baseUrl}/list`);
  }

  obtenerRuta(id: number): Observable<RutaDTO> {
    return this.http.get<RutaDTO>(`${this.baseUrl}/${id}`);
  }
}
