import {Injectable} from '@angular/core';
import {LoginModel} from '../model/login-model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AutorizacaoService {

  constructor(private http: HttpClient) {
  }

  private API_URL: string = "http://localhost:8080/auth/login";

  realizarLogin(dadosLogin: LoginModel) {
    return this.http.post(this.API_URL, dadosLogin);
  }
}
