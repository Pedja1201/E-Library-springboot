import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroupDirective, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Library } from 'src/app/model/library';
import { LoginService } from 'src/app/services/auth/login.service';
import { LibrariesService } from 'src/app/services/library/libraries.service';

@Component({
  selector: 'app-add-libraries',
  templateUrl: './add-libraries.component.html',
  styleUrls: ['./add-libraries.component.css']
})
export class AddLibrariesComponent implements OnInit {
  library : Library = new Library();
  errorMessage : string = '';
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });

  constructor(
    private _formBuilder: FormBuilder,
    private service: LibrariesService,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
  }

  create(library: Library) {
    if(library !== undefined) {
      this.service.create(library).subscribe({
        next : (value: any) => { this.router.navigate(['/libraries']) }, 
        error: (error) => {this.errorMessage = error.error}});
    }
  }
  goBack(){
    this.router.navigate(['/libraries']);  
  }

}
