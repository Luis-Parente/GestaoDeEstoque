import {Component} from '@angular/core';
import {AuthService} from '../../services/auth-service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from '../../model/login-request';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-login-component',
  imports: [
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {

  constructor(private service: AuthService, private router: Router) {
  }

  loginForm = {
    email: '',
    senha: ''
  }

  submeterLogin() {
    const dadosLogin: LoginRequest = {
      email: this.loginForm.email,
      senha: this.loginForm.senha
    }

    const resposta = this.service.realizarLogin(dadosLogin).subscribe({
      next: resposta => {
        this.router.navigate(['home']);
      },
      error: (erro: HttpErrorResponse) => {
        alert(erro.error.message);
      }
    });
  }

  limparLoginForm() {
    this.loginForm.email = '';
    this.loginForm.senha = '';
  }
}
