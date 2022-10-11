import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Library } from 'src/app/model/library';
import { LoginService } from 'src/app/services/auth/login.service';
import { BooksService } from 'src/app/services/books/books.service';

@Component({
  selector: 'app-edit-books',
  templateUrl: './edit-books.component.html',
  styleUrls: ['./edit-books.component.css']
})
export class EditBooksComponent implements OnInit {
  book : Book = new Book();
  libraries: Library[] = [];

  errorMessage : string = '';
  constructor(
    private service: BooksService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let bookId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(bookId).subscribe((value: any) => {
      this.book = value;
    })
  }


  update = (book:Book) => {
    if (book.id !== undefined) {
      this.service.update(book.id, book).subscribe({next: () => {
        this.service.getAll();
        this.router.navigate(['/books']);
      },
      error: (error) => {this.errorMessage = error.error}});
    }}

  goBack(){
    this.router.navigate(['/books']);  
  }

  comparator(book1: any, book2:any) {
    return book1 && book2
    ? book1.id === book2.id
    : book1 === book2;
  }


}
