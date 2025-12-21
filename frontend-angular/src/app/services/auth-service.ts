import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of, tap} from 'rxjs';
import {environment} from '../../environments/environment';
import {LoginRequest} from '../model/login-request';
import {LoginResponse} from '../model/login-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  getToken(): string | null {
    return sessionStorage.getItem("token");
  }

  setToken(token: string): void {
    sessionStorage.setItem("token", token);
  }

  validarToken(): Observable<boolean> {
    const tokenStorage = this.getToken();

    if (!tokenStorage) {
      return of(false);
    }

    const tokenApi: LoginResponse = {
      token: tokenStorage,
    }

    return this.http.post<boolean>(`${environment.API_URL}/auth/validar`, tokenApi)
  }

  realizarLogin(dadosLogin: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.API_URL}/auth/login`, dadosLogin)
      .pipe(tap(resposta => {
        this.setToken(resposta.token);
      }));
  }

  realizarLogout(): void {
    sessionStorage.removeItem("token");
  }
}
