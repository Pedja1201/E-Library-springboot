import { Component } from '@angular/core';
import { LoginService } from './services/auth/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FrontLibrary';

  constructor(public loginService : LoginService){}

  ngOnInit(){
    this.loginService.isLoggedIn()
  }
}
