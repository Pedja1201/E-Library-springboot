import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Customer } from 'src/app/model/customer';
import { Order } from 'src/app/model/order';
import { LoginService } from 'src/app/services/auth/login.service';
import { OrdersService } from 'src/app/services/orders/orders.service';

@Component({
  selector: 'app-edit-orders',
  templateUrl: './edit-orders.component.html',
  styleUrls: ['./edit-orders.component.css']
})
export class EditOrdersComponent implements OnInit {
  order : Order = new Order();
  customers: Customer[] = [];
  books: Book[] = [];

  errorMessage : string = '';
  constructor(
    private service: OrdersService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let orderId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(orderId).subscribe((value: any) => {
      this.order = value;
    })
  }

  update = (order:Order) => {
    if (order.id !== undefined) {
      this.service.update(order.id, order).subscribe({next: () => {
        this.service.getAll();
        this.router.navigate(['/orders']);
      },
      error: (error) => {this.errorMessage = error.error}});
    }}

  goBack(){
    this.router.navigate(['/orders']);  
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
