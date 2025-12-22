import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from '../services/auth-service';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate {

  constructor(private service: AuthService, private router: Router) {
  }

  canActivate(): Observable<boolean> {
    return this.service.validarToken().pipe(
      map(isValid => {
        if (!isValid) {
          this.router.navigate(['/login']);
          return false;
        }
        return true;
      })
    );
  }
}
