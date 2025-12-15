import { Routes } from '@angular/router';
import {LoginComponent} from './componentes/login-component/login-component';
import {PaginaInicialComponent} from './componentes/pagina-inicial-component/pagina-inicial-component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: PaginaInicialComponent}
];
