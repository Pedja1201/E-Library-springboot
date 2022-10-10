import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { LoginService } from 'src/app/services/auth/login.service';
import { UsersService } from 'src/app/services/users/users.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user : User = new User();
  errorMessage : string = '';
  
  constructor(
    private service: UsersService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  createUser(user: User) {
    if(user !== undefined) {
      this.service.create(user).subscribe({
        next : (value: any) => { this.router.navigate(['/users']) }, 
        error: (error) => {this.errorMessage = error.error}
      })
    }
  }

  goBackUsers(){
    this.router.navigate(['/users']);  
  }

}
