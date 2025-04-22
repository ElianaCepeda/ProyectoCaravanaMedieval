// src/app/services-back/caravana.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Caravana } from '../Models/caravana';

@Injectable({
  providedIn: 'root'
})
export class CaravanaService {
  private readonly baseUrl = 'http://localhost:8081/caravana';

  constructor(private http: HttpClient) {}

  /** Crea una nueva caravana */
  crearCaravana(caravana: Caravana): Observable<Caravana> {
    return this.http.post<Caravana>(`${this.baseUrl}/create`, caravana);
  }

  /** Lista todas las caravanas */
  obtenerCaravanas(): Observable<Caravana[]> {
    return this.http.get<Caravana[]>(`${this.baseUrl}/list`);
  }

  /** Obtiene una sola caravana por ID */
  obtenerCaravana(id: number): Observable<Caravana> {
    return this.http.get<Caravana>(`${this.baseUrl}/${id}`);
  }

  /** Elimina una caravana por ID */
  eliminarCaravana(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  /** Actualiza una caravana por ID */
  actualizarCaravana(id: number, caravana: Caravana): Observable<Caravana> {
    return this.http.put<Caravana>(`${this.baseUrl}/update/${id}`, caravana);
  }
}
