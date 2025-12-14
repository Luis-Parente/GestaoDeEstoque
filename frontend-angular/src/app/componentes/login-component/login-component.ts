import {Component} from '@angular/core';
import {LoginService} from '../../services/login-service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginModel} from '../../model/login-model';

@Component({
  selector: 'app-login-component',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {

  constructor(private service: LoginService) {
  }

  loginForm = {
    email: '',
    senha: ''
  }

  async submeterLogin() {
    const dadosLogin: LoginModel = new LoginModel(this.loginForm.email, this.loginForm.senha)

    const resposta = await this.service.realizarLogin(dadosLogin);

    console.log(resposta);
  }
}
