import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin, AdminPage } from 'src/app/model/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminsService {

  private baseUrl = "/api/admins"

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<AdminPage<Admin>>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Admin[]>(`${this.baseUrl}/${id}`)
  }

  create(admin : Admin){
    return this.client.post(this.baseUrl, admin)
  }

  update(id:number, admin : Admin){
    return this.client.put<Admin[]>(`${this.baseUrl}/${id}`, admin)
  }

  delete(admin:Admin){
    return this.client.delete<Admin[]>(`${this.baseUrl}/${admin.id}`)
  }
}
