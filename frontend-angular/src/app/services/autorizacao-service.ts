import {Injectable} from '@angular/core';
import {LoginModel} from '../model/login-model';
import {HttpClient} from '@angular/common/http';
import {TokenDecodificado} from '../model/token-decodificado';
import {catchError, map, Observable, of} from 'rxjs';
import {environment} from '../../environments/environment';
import {TokenModel} from '../model/token-model';

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

  validarToken(): Observable<boolean> {
    const tokenStorage = this.getToken()
    const token: TokenModel = {
      token: ''
    };

    if (tokenStorage) {
      token.token = tokenStorage;
    } else {
      return of(false);
    }

    return this.http.post<TokenDecodificado>(`${environment.API_URL}/auth/validar`, token)
      .pipe(
        map((resposta) => {
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

  realizarLogin(dadosLogin: LoginModel): Observable<boolean> {
    return this.http.post<TokenModel>(`${environment.API_URL}/auth/login`, dadosLogin)
      .pipe(
        map((resposta) => {
          this.setToken(resposta.token);
          return true;
        }),
        catchError((erro) => {
          console.log(erro)
          return of(false);
        })
      );
  }

  realizarLogout() {
    sessionStorage.removeItem("token");
  }
}
