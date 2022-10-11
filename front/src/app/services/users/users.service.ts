import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User, UserPage } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = "/api/users"

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<UserPage<User>>(this.baseUrl)
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

  delete(us:User){
    return this.client.delete<User[]>(`${this.baseUrl}/${us.id}`)
  }
}
