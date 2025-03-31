import { Routes } from '@angular/router';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { CarCreateComponent } from './car-create/car-create.component';

export const routes: Routes = [
  {
    path: 'welcome', 
    component: WelcomePageComponent
  },
  {
    path: 'caravan-create', 
    component: CarCreateComponent
  }
];
