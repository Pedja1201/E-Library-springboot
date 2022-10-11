import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { LoginService } from 'src/app/services/auth/login.service';
import { UsersService } from 'src/app/services/users/users.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user : User = new User();
  errorMessage : string = '';

  constructor(
    private service: UsersService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let userId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(userId).subscribe((value: any) => {
      this.user = value;
    })
  }

  update(user: User) {
    if (user.id !== undefined) {
      this.service.update(user.id, user).subscribe({next: () => {
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
