import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer, CustomerPage } from 'src/app/model/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  private baseUrl = "/api/customers"

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<CustomerPage<Customer>>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Customer[]>(`${this.baseUrl}/${id}`)
  }

  create(customer : Customer){
    return this.client.post(this.baseUrl, customer)
  }

  update(id:number, customer : Customer){
    return this.client.put<Customer[]>(`${this.baseUrl}/${id}`, customer)
  }

  delete(customer:Customer){
    return this.client.delete<Customer[]>(`${this.baseUrl}/${customer.id}`)
  }
}
