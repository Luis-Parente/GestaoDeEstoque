import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from './auth-service';
import {map, Observable} from 'rxjs';
import {Produto} from '../model/produto';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  buscarProdutos(): Observable<Produto[]> {
    const token = this.authService.getToken();

    const headers = new HttpHeaders().append('Authorization', `Bearer ${token}`);

    return this.http.get<Produto[]>(`${environment.API_URL}/produto`, {headers});
  }
}
