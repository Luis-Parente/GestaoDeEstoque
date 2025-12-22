import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs';
import {AuthService} from '../../services/auth-service';

@Component({
  selector: 'app-cabecalho-component',
  imports: [],
  templateUrl: './cabecalho-component.html',
  styleUrl: './cabecalho-component.css',
})
export class CabecalhoComponent implements OnInit {
  mostrarBotaoSair: boolean = true;

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.verificarRotaAtual();
    });

    this.verificarRotaAtual();
  }

  verificarRotaAtual() {
    const rotaAtual = this.router.url;
    this.mostrarBotaoSair = !rotaAtual.includes('/login');
  }

  logout() {
    this.authService.realizarLogout();
    this.router.navigate(['login'])
  }
}
