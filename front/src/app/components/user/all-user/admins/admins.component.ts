import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { AdminsService } from 'src/app/services/admins/admins.service';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  admins : Admin[] = [];

  constructor(private service : AdminsService, private router : Router) { }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.admins = value.content }
    });
  }


  delete = (admin: Admin) => {
    this.service.delete(admin)
      .subscribe(resp => this.getAll());
  }

  edit(admin:Admin){
    this.router.navigate(['/edit-admin', admin.id])
  }

  addForm(){
    this.router.navigate(['/add-admin'])
  }

}
