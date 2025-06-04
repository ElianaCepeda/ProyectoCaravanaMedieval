import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cuenta } from '../dto/cuenta-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CuentaService {
  constructor(private http: HttpClient) {}

  private options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  retirar(id: number, cantidad: number): Observable<number> {
    return this.http.post<number>(
      `${environment.serverUrl}/cuenta/${id}/retirar`,
      cantidad,
      this.options
    );
  }

  abonar(id: number, cantidad: number): Observable<number> {
    return this.http.post<number>(
      `${environment.serverUrl}/cuenta/${id}/abonar`,
      cantidad,
      this.options
    );
  }

  saldo(id: number): Observable<number> {
    return this.http.get<number>(`${environment.serverUrl}/cuenta/${id}/saldo`);
  }

  findById(id: number): Observable<Cuenta> {
    return this.http.get<Cuenta>(`${environment.serverUrl}/cuenta/${id}`);
  }

  findAll(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(`${environment.serverUrl}/cuenta`);
  }
}
