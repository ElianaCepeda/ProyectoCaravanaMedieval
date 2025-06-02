import { TestBed } from '@angular/core/testing';
import { ClockService } from './clock.service';
import { fakeAsync, tick } from '@angular/core/testing';

describe('ClockService', () => {
  let service: ClockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClockService);
  });

  afterEach(() => {
    service.ngOnDestroy(); // Evita intervalos activos después del test
  });

  it('debería crearse correctamente', () => {
    expect(service).toBeTruthy();
  });

  it('debería iniciar con 06:30', () => {
    expect(service.getCurrentTime()).toBe('06:30');
  });

  it('debería avanzar 1 minuto por segundo', fakeAsync(() => {
    tick(1000); // Avanza 1 segundo real
    expect(service.getCurrentTime()).toBe('06:31');

    tick(29_000); // Avanza 29 segundos más
    expect(service.getCurrentTime()).toBe('07:00');
  }));

  it('debería emitir el tiempo por displayTime$', fakeAsync(() => {
    let latestValue = '';
    service.displayTime$.subscribe(value => latestValue = value);

    tick(1000);
    expect(latestValue).toBe('06:31');

    tick(60_000); // 1 hora más
    expect(latestValue).toBe('07:31');
  }));

  it('debería permitir configurar la hora manualmente', () => {
    service.setTime(15, 45);
    expect(service.getCurrentTime()).toBe('15:45');
  });

  it('debería hacer wrap de 24h y 60min correctamente', () => {
    service.setTime(23, 59);
    tick(1000);
    expect(service.getCurrentTime()).toBe('00:00');
  });
});
