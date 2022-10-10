import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage : string = '';
  constructor(public loginService : LoginService, private router : Router) { }

  loginForma : FormGroup = new FormGroup({
    "username": new FormControl(null, Validators.required),
    "password": new FormControl(null, Validators.required),
  });


  ngOnInit(): void {
  }

  login(){
    if(this.loginForma.valid){
      this.loginService.login(this.loginForma.value).subscribe(
        {
          next: () => {this.router.navigate(["/libraries"])},
          error: (error) => {this.errorMessage = error.error}
        }
      );
    }
  }

}
