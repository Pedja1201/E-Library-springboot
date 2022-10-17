import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order, OrderPage } from 'src/app/model/order';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private baseUrl = "/api/orders"

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<OrderPage<Order>>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Order[]>(`${this.baseUrl}/${id}`)
  }

  create(order : Order){
    return this.client.post(this.baseUrl, order)
  }

  update(id:number, order : Order){
    return this.client.put<Order[]>(`${this.baseUrl}/${id}`, order)
  }

  delete(ord:Order){
    return this.client.delete<Order[]>(`${this.baseUrl}/${ord.id}`)
  }
}
