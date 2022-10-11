import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Customer } from 'src/app/model/customer';
import { Rent } from 'src/app/model/rent';
import { LoginService } from 'src/app/services/auth/login.service';
import { RentsService } from 'src/app/services/rents/rents.service';

@Component({
  selector: 'app-edit-rents',
  templateUrl: './edit-rents.component.html',
  styleUrls: ['./edit-rents.component.css']
})
export class EditRentsComponent implements OnInit {
  rent : Rent = new Rent();
  customers: Customer[] = [];
  books: Book[] = [];

  errorMessage : string = '';
  constructor(
    private service: RentsService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let rentId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(rentId).subscribe((value: any) => {
      this.rent = value;
    })
  }

  update = (rent:Rent) => {
    if (rent.id !== undefined) {
      this.service.update(rent.id, rent).subscribe({next: () => {
        this.service.getAll();
        this.router.navigate(['/rents']);
      },
      error: (error) => {this.errorMessage = error.error}});
    }}

  goBack(){
    this.router.navigate(['/rents']);  
  }

  comparator(cust1: any, cust2:any) {
    return cust1 && cust2
    ? cust1.id === cust2.id
    : cust1 === cust2;
  }
  comparator2(book1: any, book2:any) {
    return book1 && book2
    ? book1.id === book2.id
    : book1 === book2;
  }
}
