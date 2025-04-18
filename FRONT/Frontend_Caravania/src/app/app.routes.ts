import { Routes } from '@angular/router';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { CarCreateComponent } from './car-create/car-create.component';
import { LoadingScreenComponent } from './loading-screen/loading-screen.component';
import { RoleSelectComponent } from './role-select/role-select.component';
import { MapComponent } from './map/map.component';
import { ServicesComponent } from './services/services.component';
import { CommerceComponent } from './commerce/commerce.component';

export const routes: Routes = [
  {
    path: 'welcome', 
    component: WelcomePageComponent
  },
  {
    path: 'caravan-create', 
    component: CarCreateComponent
  },
  {
    path: 'loading', 
    component: LoadingScreenComponent
  },
  {
    path: 'role', 
    component: RoleSelectComponent
  },
  {
    path: 'mapCaravania', 
    component: MapComponent
  },
  { 
    path: 'services',
    component: ServicesComponent
  },
  { 
    path: 'commerce',
    component: CommerceComponent
  }
];
