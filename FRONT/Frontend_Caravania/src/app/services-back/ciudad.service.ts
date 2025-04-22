import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ciudad } from '../Models/ciudad';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  private readonly baseUrl = 'http://localhost:8081/ciudad';

  constructor(private http: HttpClient) { }

  obtenerCiudades(): Observable<Ciudad[]> {
    return this.http.get<Ciudad[]>(`${this.baseUrl}/list`);
  }

  obtenerCiudad(id: number): Observable<Ciudad> {
    return this.http.get<Ciudad>(`${this.baseUrl}/${id}`);
  }

  crearCiudad(ciudad: Ciudad): Observable<Ciudad> {
    return this.http.post<Ciudad>(`${this.baseUrl}/create`, ciudad);
  }

  eliminarCiudad(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
