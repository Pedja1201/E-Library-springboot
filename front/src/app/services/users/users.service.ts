import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = "/api/users"

  constructor(private client : HttpClient) { }

  getAll() : Observable<User[]>{
    return this.client.get<User[]>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<User[]>(`${this.baseUrl}/${id}`)
  }

  create(user : User){
    return this.client.post(this.baseUrl, user)
  }

  update(id:number, user : User){
    return this.client.put<User[]>(`${this.baseUrl}/${id}`, user)
  }

  delete(id:number){
    return this.client.delete<User[]>(`${this.baseUrl}/${id}`)
  }
}
