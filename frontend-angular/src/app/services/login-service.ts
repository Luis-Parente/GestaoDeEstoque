import {Injectable} from '@angular/core';
import {LoginModel} from '../model/login-model';
import axios from 'axios';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private API_URL: string = "http://localhost:8080/login";

  async realizarLogin(dadosLogin: LoginModel) {
    const response = await axios.post(this.API_URL, dadosLogin);
    return await response.data;
  }
}
