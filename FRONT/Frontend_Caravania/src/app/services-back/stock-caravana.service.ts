import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { StockCaravana } from '../Models/stock-caravana';

@Injectable({
  providedIn: 'root'
})
export class StockCaravanaService {
  private readonly baseUrl = 'http://localhost:8081/stockCaravana'; 

  constructor(private http: HttpClient) {}

  /** Obtiene todos los objetos de una caravana */
  obtenerStockPorCaravana(caravanaId: number): Observable<StockCaravana[]> {
    return this.http.get<StockCaravana[]>(`${this.baseUrl}/caravana/${caravanaId}`);
  }

  /** Añade o actualiza un objeto en el inventario */
  guardarObjeto(stock: StockCaravana): Observable<StockCaravana> {
    return this.http.post<StockCaravana>(`${this.baseUrl}/guardar`, stock);
  }

  actualizarObjeto(id: number, stock: StockCaravana): Observable<StockCaravana> {
  return this.http.put<StockCaravana>(`${this.baseUrl}/${id}`, stock);}

  /** Elimina un objeto del inventario de la caravana */
  eliminarObjeto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminar/${id}`);
  }
}
