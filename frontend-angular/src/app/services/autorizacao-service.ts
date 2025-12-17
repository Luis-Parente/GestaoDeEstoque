import {Injectable} from '@angular/core';
import {LoginModel} from '../model/login-model';
import {HttpClient} from '@angular/common/http';
import {TokenDecodificado} from '../model/token-decodificado';
import {catchError, map, Observable, of} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AutorizacaoService {

  constructor(private http: HttpClient) {
  }

  getToken() {
    return sessionStorage.getItem("token");
  }

  setToken(token: string) {
    sessionStorage.setItem("token", token);
  }

  realizarLogin(dadosLogin: LoginModel) {
    return this.http.post(`${environment.API_URL}/auth/login`, dadosLogin);
  }

  realizarLogout() {
    sessionStorage.removeItem("token");
  }

  validarToken(): Observable<boolean> {
    const token = this.getToken()

    if (!token) {
      return of(false);
    }

    return this.http.post(`${environment.API_URL}/auth/validar`, {token: token}).pipe(map((resposta: any) => {
        const tokenDecodificado: TokenDecodificado = {
          email: resposta.email,
          expiracao: new Date(resposta.expiracao)
        };
        return tokenDecodificado.expiracao > new Date();
      }),
      catchError((err) => {
        console.log(err);
        return of(false);
      })
    );
  }
}
