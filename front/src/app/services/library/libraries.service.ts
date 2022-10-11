import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Library, LibraryPage } from 'src/app/model/library';

@Injectable({
  providedIn: 'root'
})
export class LibrariesService {
  private baseUrl = "/api/libraries"

  constructor(private client : HttpClient) { }

  getAll(){
    return this.client.get<LibraryPage<Library>>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Library[]>(`${this.baseUrl}/${id}`)
  }

  create(library : Library){
    return this.client.post(this.baseUrl, library)
  }

  update(id:number, library : Library){
    return this.client.put<Library[]>(`${this.baseUrl}/${id}`, library)
  }

  delete(lib:Library){
    return this.client.delete<Library[]>(`${this.baseUrl}/${lib.id}`)
  }
}
