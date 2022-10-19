import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { AdminsService } from 'src/app/services/admins/admins.service';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'app-edit-admins',
  templateUrl: './edit-admins.component.html',
  styleUrls: ['./edit-admins.component.css']
})
export class EditAdminsComponent implements OnInit {
  admin : Admin = new Admin();
  errorMessage : string = '';

  constructor(
    private service: AdminsService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let userId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(userId).subscribe((value: any) => {
      this.admin = value;
    })
  }

  update(admin: Admin) {
    if (admin.id !== undefined) {
      this.service.update(admin.id, admin).subscribe({next: () => {
        this.service.getAll();
        this.router.navigate(['/users']);
      },
      error: (error) => {this.errorMessage = error.error}});
    }
  }

  goBack(){
    this.router.navigate(['/users']);
    // this.location.back();  
  }

}
