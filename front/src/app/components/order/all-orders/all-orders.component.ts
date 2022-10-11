import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { OrdersService } from 'src/app/services/orders/orders.service';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
})
export class AllOrdersComponent implements OnInit {
  orders : Order[] = [];

  constructor(
    private service : OrdersService,
     private router : Router) {
    
   }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.orders = value.content }
    });
  }


  delete = (order: Order) => {
    this.service.delete(order)
      .subscribe(resp => this.getAll());
  }

  edit(order:Order){
    this.router.navigate(['/edit-order', order.id])
  }

  addForm(){
    this.router.navigate(['/add-order'])
  }

}
