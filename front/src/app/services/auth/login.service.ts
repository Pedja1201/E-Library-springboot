import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { Admin } from 'src/app/model/admin';
import { Customer } from 'src/app/model/customer';
import { Token } from 'src/app/model/token';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = "/api"
  token : Token = new Token()
  user : User = new User()

  isLoggedIn(){
    if (localStorage.getItem('Token')) {
      return true;
    } else {
      return false;
    }
  }

  constructor(private client : HttpClient, private router : Router) { }

  login(user:User){
    const headers=new HttpHeaders({'Access-Control-Allow-Origin':'*'});
    return this.client.post<User>(`${this.baseUrl}/login`, user,{headers:headers}).pipe(
      map(user => {
        localStorage.setItem('Token', JSON.stringify(user));
      })
    );
  }

  signupAdmin(user:Admin) : Observable<string>{ 
    return this.client.post<string>(`${this.baseUrl}/registerAdmin`, user)
  }
  signupCustomer(user:Customer) : Observable<string>{ 
    return this.client.post<string>(`${this.baseUrl}/registerCustomer`, user)
  }

  logout() {
    localStorage.removeItem("Token");
    this.router.navigate(['/login'])
  }
}
