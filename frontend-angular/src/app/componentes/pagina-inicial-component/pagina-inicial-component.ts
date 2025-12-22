import { Component } from '@angular/core';
import {ProdutoComponent} from '../produto-component/produto-component';

@Component({
  selector: 'app-pagina-inicial-component',
  imports: [
    ProdutoComponent
  ],
  templateUrl: './pagina-inicial-component.html',
  styleUrl: './pagina-inicial-component.css',
})
export class PaginaInicialComponent {

}
