import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { caravaneroGuard } from './caravanero.guard';

describe('caravaneroGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => caravaneroGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
