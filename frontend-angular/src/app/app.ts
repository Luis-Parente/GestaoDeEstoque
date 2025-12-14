import {Component, signal} from '@angular/core';
import {LoginComponent} from './componentes/login-component/login-component';

@Component({
  selector: 'app-root',
  imports: [LoginComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-angular');
}
