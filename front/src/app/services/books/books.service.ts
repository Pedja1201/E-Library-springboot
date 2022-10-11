import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book, BookPage } from 'src/app/model/book';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private baseUrl = "/api/books"

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<BookPage<Book>>(this.baseUrl)
  }

  getOne(id : number){
    return this.client.get<Book[]>(`${this.baseUrl}/${id}`)
  }

  create(book : Book){
    return this.client.post(this.baseUrl, book)
  }

  update(id:number, book : Book){
    return this.client.put<Book[]>(`${this.baseUrl}/${id}`, book)
  }

  delete(book:Book){
    return this.client.delete<Book[]>(`${this.baseUrl}/${book.id}`)
  }
}
