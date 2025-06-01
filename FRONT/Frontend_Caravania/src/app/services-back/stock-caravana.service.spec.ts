import { TestBed } from '@angular/core/testing';

import { StockCaravanaService } from './stock-caravana.service';

describe('StockCaravanaService', () => {
  let service: StockCaravanaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockCaravanaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
