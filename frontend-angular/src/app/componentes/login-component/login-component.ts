import {Component} from '@angular/core';
import {AutorizacaoService} from '../../services/autorizacao-service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginModel} from '../../model/login-model';
import {Router} from '@angular/router';

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

  constructor(private service: AutorizacaoService, private router: Router) {
  }

  loginForm = {
    email: '',
    senha: ''
  }

  submeterLogin() {
    const dadosLogin: LoginModel = new LoginModel(this.loginForm.email, this.loginForm.senha)

    this.service.realizarLogin(dadosLogin).subscribe({
      next: (resposta: any) => {
        sessionStorage.setItem('token', JSON.stringify(resposta.token));
        this.router.navigate(['/home'])
      },
      error: err => {
        console.log(err);
      }
    })
  }

  navegar() {
    this.router.navigate(['/home']);
  }
}
