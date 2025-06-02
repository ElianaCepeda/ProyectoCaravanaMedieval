// src/app/services-back/clock.service.ts
import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClockService implements OnDestroy {
  private currentHour: number = 1;
  private currentMinute: number = 10;
  private displaySubject = new BehaviorSubject<string>(this.formatTime());
  public displayTime$: Observable<string> = this.displaySubject.asObservable();

  private intervaloId: any;

  constructor() {
    this.startClock();
  }

  /** Inicia el intervalo que cada segundo suma 1 minuto ficticio */
  private startClock(): void {
    // Emitir la hora inicial inmediatamente
    this.displaySubject.next(this.formatTime());

    // Cada 1000 ms acumulamos 1 minuto
    this.intervaloId = setInterval(() => {
      this.currentMinute++;
      if (this.currentMinute >= 60) {
        this.currentMinute = 0;
        this.currentHour = (this.currentHour + 1) % 24;
      }
      // Emitir el nuevo “HH:MM” a todos los suscriptores
      this.displaySubject.next(this.formatTime());
    }, 1000);
  }

  /** Detiene el intervalo (para evitar fugas de memoria) */
  ngOnDestroy(): void {
    clearInterval(this.intervaloId);
  }

  /** Retorna el string formateado “HH:MM” */
  private formatTime(): string {
    const hh = this.currentHour.toString().padStart(2, '0');
    const mm = this.currentMinute.toString().padStart(2, '0');
    return `${hh}:${mm}`;
  }

  /** Opcional: método para forzar ajustar la hora */
  public setTime(hour: number, minute: number): void {
    this.currentHour = hour % 24;
    this.currentMinute = minute % 60;
    this.displaySubject.next(this.formatTime());
  }

  /** Opcional: getters si prefieres acceder sin suscripción */
  public getCurrentTime(): string {
    return this.formatTime();
  }

  public sumarHoras(horas: number): void {
  this.currentHour = (this.currentHour + horas) % 24;
  this.displaySubject.next(this.formatTime());
}
}
