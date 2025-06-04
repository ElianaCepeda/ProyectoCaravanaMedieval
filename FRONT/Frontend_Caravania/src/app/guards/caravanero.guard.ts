import { CanActivateFn } from '@angular/router';

export const caravaneroGuard: CanActivateFn = (route, state) => {
  return true;
};
