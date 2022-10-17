import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/model/customer';
import { CustomersService } from 'src/app/services/customers/customers.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers : Customer[] = [];

  constructor(private service : CustomersService, private router : Router) { }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.customers = value.content }
    });
  }


  delete = (customer: Customer) => {
    this.service.delete(customer)
      .subscribe(resp => this.getAll());
  }

  edit(customer:Customer){
    this.router.navigate(['/edit-customer', customer])
  }

  addForm(){
    this.router.navigate(['/add-customer'])
  }

}
