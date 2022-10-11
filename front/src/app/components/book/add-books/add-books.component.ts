import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroupDirective, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Library } from 'src/app/model/library';
import { LoginService } from 'src/app/services/auth/login.service';
import { BooksService } from 'src/app/services/books/books.service';

@Component({
  selector: 'app-add-books',
  templateUrl: './add-books.component.html',
  styleUrls: ['./add-books.component.css']
})
export class AddBooksComponent implements OnInit {

  book : Book = new Book();
  libraries: Library[] = [];
  errorMessage : string = '';
  // @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  constructor(
    private _formBuilder: FormBuilder,
    private service: BooksService,
    // private bookService: BooksService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  create(book: Book) {
    if(book !== undefined) {
      this.service.create(book).subscribe({
        next : (value: any) => { this.router.navigate(['/books']) }, 
        error: (error) => {this.errorMessage = error.error}});
    }
  }

  goBack(){
    this.router.navigate(['/books']);  
  }

}
