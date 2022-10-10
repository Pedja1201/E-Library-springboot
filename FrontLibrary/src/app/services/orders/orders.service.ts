import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/app/model/order';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private baseUrl = "/api/orders"

  constructor(private client : HttpClient) { }

  getAll() : Observable<Order[]>{
    return this.client.get<Order[]>(this.baseUrl)
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

  delete(id:number){
    return this.client.delete<Order[]>(`${this.baseUrl}/${id}`)
  }
}
