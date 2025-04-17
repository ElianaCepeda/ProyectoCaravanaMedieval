import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityPopupComponent } from './city-popup.component';

describe('CityPopupComponent', () => {
  let component: CityPopupComponent;
  let fixture: ComponentFixture<CityPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CityPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CityPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
