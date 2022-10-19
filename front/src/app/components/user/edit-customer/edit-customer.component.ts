import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/model/customer';
import { LoginService } from 'src/app/services/auth/login.service';
import { CustomersService } from 'src/app/services/customers/customers.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  customer : Customer = new Customer();
  errorMessage : string = '';

  constructor(
    private service: CustomersService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let userId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(userId).subscribe((value: any) => {
      this.customer = value;
    })
  }

  update(customer: Customer) {
    if (customer.id !== undefined) {
      this.service.update(customer.id, customer).subscribe({next: () => {
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
