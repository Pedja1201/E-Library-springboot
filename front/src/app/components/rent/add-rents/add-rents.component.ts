import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Customer } from 'src/app/model/customer';
import { Rent } from 'src/app/model/rent';
import { LoginService } from 'src/app/services/auth/login.service';
import { RentsService } from 'src/app/services/rents/rents.service';

@Component({
  selector: 'app-add-rents',
  templateUrl: './add-rents.component.html',
  styleUrls: ['./add-rents.component.css']
})
export class AddRentsComponent implements OnInit {

  rent : Rent = new Rent();
  customers: Customer[] = [];
  books: Book[] = [];
  errorMessage : string = '';
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  constructor(
    private _formBuilder: FormBuilder,
    private service: RentsService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  create(rent: Rent) {
    if(rent !== undefined) {
      this.service.create(rent).subscribe({
        next : (value: any) => { this.router.navigate(['/rents']) }, 
        error: (error) => {this.errorMessage = error.error}});
    }
  }

  goBack(){
    this.router.navigate(['/rents']);  
  }

}
