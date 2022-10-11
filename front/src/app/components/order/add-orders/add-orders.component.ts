import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroupDirective, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Customer } from 'src/app/model/customer';
import { Order } from 'src/app/model/order';
import { LoginService } from 'src/app/services/auth/login.service';
import { OrdersService } from 'src/app/services/orders/orders.service';

@Component({
  selector: 'app-add-orders',
  templateUrl: './add-orders.component.html',
  styleUrls: ['./add-orders.component.css']
})
export class AddOrdersComponent implements OnInit {
  order : Order = new Order();
  customers: Customer[] = [];
  books: Book[] = [];
  errorMessage : string = '';
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  constructor(
    private _formBuilder: FormBuilder,
    private service: OrdersService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  create(order: Order) {
    if(order !== undefined) {
      this.service.create(order).subscribe({
        next : (value: any) => { this.router.navigate(['/orders']) }, 
        error: (error) => {this.errorMessage = error.error}});
    }
  }

  goBack(){
    this.router.navigate(['/orders']);  
  }

}
