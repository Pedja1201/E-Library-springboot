import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { Customer } from 'src/app/model/customer';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  errorMessages : string = '';

  constructor(
    public loginService : LoginService,
    private router : Router) {  }

  ngOnInit(): void {
  }

  createAdmin(user: Admin) {
    if(user !== undefined) {
      this.loginService.signupAdmin(user).subscribe({
        next: (value:any) => { this.router.navigate(['/login']);  },
        error: (error) => {this.errorMessages = error.error}})
    }
  }

  createCustomer(user: Customer) {
    if(user !== undefined) {
      this.loginService.signupCustomer(user).subscribe({
        next: (value:any) => { this.router.navigate(['/login']);  },
        error: (error) => {this.errorMessages = error.error}})
    }
  }

}
