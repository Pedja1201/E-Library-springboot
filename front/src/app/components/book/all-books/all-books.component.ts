import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { BooksService } from 'src/app/services/books/books.service';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.css']
})
export class AllBooksComponent implements OnInit {
  books : Book[] = [];

  constructor(
    private service : BooksService,
    public snackBar:MatSnackBar,
     private router : Router) {
    
   }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.books = value.content }
    });
  }


  delete = (book: Book) => {
    this.service.delete(book)
      .subscribe(resp => this.getAll());
  }

  edit(book:Book){
    this.router.navigate(['/edit-book', book.id])
  }

  addForm(){
    this.router.navigate(['/add-book'])
  }

}
