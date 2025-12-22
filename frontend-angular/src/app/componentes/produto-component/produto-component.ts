import {Component, OnInit} from '@angular/core';
import {ProdutoService} from '../../services/produto-service';
import {Produto} from '../../model/produto';
import {CommonModule} from '@angular/common';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-produto-component',
  imports: [CommonModule],
  templateUrl: './produto-component.html',
  styleUrl: './produto-component.css',
})
export class ProdutoComponent implements OnInit {

  produtos$!: Observable<Produto[]>;

  carregando: boolean = true;


  constructor(private produtoService: ProdutoService) {
  }

  ngOnInit(): void {
    this.buscarProdutos();
  }

  buscarProdutos(): void {
    this.produtos$ = this.produtoService.buscarProdutos();
    this.carregando = false;
  }
}
