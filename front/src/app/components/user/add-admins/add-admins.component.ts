import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { AdminsService } from 'src/app/services/admins/admins.service';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'app-add-admins',
  templateUrl: './add-admins.component.html',
  styleUrls: ['./add-admins.component.css']
})
export class AddAdminsComponent implements OnInit {
  admin : Admin = new Admin();
  errorMessage : string = '';
  
  constructor(
    private service: AdminsService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  createAdmin(admin: Admin) {
    if(admin !== undefined) {
      this.service.create(admin).subscribe({
        next : (value: any) => { this.router.navigate(['/users']) }, 
        error: (error) => {this.errorMessage = error.error}
      })
    }
  }

  goBack(){
    this.router.navigate(['/users']);  
  }


}
