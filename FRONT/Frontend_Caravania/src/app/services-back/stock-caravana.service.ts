// src/app/services-back/stock-caravana.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// IMPORTA DESDE EL ARCHIVO stock-caravana-dto.ts
import { StockCaravanaDTO } from '../Models/stock-caravana-dto';

@Injectable({
  providedIn: 'root'
})
export class StockCaravanaService {
  private apiUrl = 'http://localhost:8081/stockcaravana';

  constructor(private http: HttpClient) {}

  /**
   * 1) Obtiene todo el stock de la caravana {caravanaId}
   *    GET http://localhost:8081/stockcaravana/caravana/{caravanaId}
   */
  obtenerStockPorCaravana(caravanaId: number): Observable<StockCaravanaDTO[]> {
    return this.http.get<StockCaravanaDTO[]>(`${this.apiUrl}/caravana/${caravanaId}`);
  }

  /**
   * 2) Aumenta la cantidad en un registro existente de stock
   *    POST http://localhost:8081/stockcaravana/comprar/{stockId}/{cantidad}
   */
  comprarStockCaravana(stockId: number, cantidad: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/comprar/${stockId}/${cantidad}`, {});
  }

  /**
   * 3) Crea un nuevo registro de StockCaravana
   *    POST http://localhost:8081/stockcaravana/crear
   *    Body: { caravanaId, productoId, cantidad }
   */
  crearStockCaravana(
    caravanaId: number,
    productoId: number,
    cantidad: number
  ): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/crear`, {
      caravanaId,
      productoId,
      cantidad
    });
  }

  venderStockCaravana(stockId: number, cantidad: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/vender/${stockId}/${cantidad}`, {});
  }

  eliminarStockCaravana(stockId: number): Observable<any> {
    // CORREGIDO: No repitas /stockcaravana
    return this.http.delete(`${this.apiUrl}/${stockId}`);
  }

  actualizarCantidadStockCaravana(stockId: number, nuevaCantidad: number): Observable<any> {
    // CORREGIDO: No repitas /stockcaravana
    return this.http.put(`${this.apiUrl}/${stockId}`, { cantidad: nuevaCantidad });
  }
}