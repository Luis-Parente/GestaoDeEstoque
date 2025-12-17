import {Component, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {RodapeComponent} from './componentes/rodape-component/rodape-component';
import {CabecalhoComponent} from './componentes/cabecalho-component/cabecalho-component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RodapeComponent, CabecalhoComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-angular');
}
