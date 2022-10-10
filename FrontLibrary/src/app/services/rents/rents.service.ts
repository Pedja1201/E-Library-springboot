import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rent } from 'src/app/model/rent';

@Injectable({
  providedIn: 'root'
})
export class RentsService {

  private baseUrl = "/api/rents"

  constructor(private client : HttpClient) { }

  getAll() : Observable<Rent[]>{
    return this.client.get<Rent[]>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Rent[]>(`${this.baseUrl}/${id}`)
  }

  create(rent : Rent){
    return this.client.post(this.baseUrl, rent)
  }

  update(id:number, rent : Rent){
    return this.client.put<Rent[]>(`${this.baseUrl}/${id}`, rent)
  }

  delete(id:number){
    return this.client.delete<Rent[]>(`${this.baseUrl}/${id}`)
  }
}
