import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { Customer } from 'src/app/model/customer';
import { LoginService } from 'src/app/services/auth/login.service';
import { CustomersService } from 'src/app/services/customers/customers.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customer : Customer = new Customer();
  errorMessage : string = '';
  
  constructor(
    private service: CustomersService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  createCustomer(customer: Customer) {
    if(customer !== undefined) {
      this.service.create(customer).subscribe({
        next : (value: any) => { this.router.navigate(['/users']) }, 
        error: (error) => {this.errorMessage = error.error}
      })
    }
  }

  goBack(){
    this.router.navigate(['/users']);  
  }


}
