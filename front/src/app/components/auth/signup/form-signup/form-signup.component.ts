import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroupDirective, FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { LoginService } from 'src/app/services/auth/login.service';
import { CustomValidators } from 'src/app/validators/auth/custom-validators';

@Component({
  selector: 'app-form-signup',
  templateUrl: './form-signup.component.html',
  styleUrls: ['./form-signup.component.css']
})
export class FormSignupComponent implements OnInit {
  user: Admin = new Admin();
  @Input()
  errorMessage : string = '';

  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  passwordsMatching = false;
  isConfirmPasswordDirty = false;
  confirmPasswordClass = 'form-control';

  forma: FormGroup = new FormGroup({
    "id": new FormControl(undefined),
    "username": new FormControl(null, [Validators.required]),
    "password": new FormControl(undefined, [Validators.required, Validators.minLength(8), Validators.maxLength(30)]),
    "confirmPassword": new FormControl(undefined, [Validators.required, Validators.minLength(8), Validators.maxLength(30)]),
    "firstName": new FormControl(undefined, [Validators.required]),
    "lastName": new FormControl(undefined, [Validators.required]),
    "email": new FormControl(undefined, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), Validators.minLength(6), Validators.maxLength(40)]),
    "ucin": new FormControl(undefined, [Validators.required])
  }, [CustomValidators.MatchValidator('password', 'confirmPassword')]
  )

  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(
    private _formBuilder: FormBuilder,
    private router: Router,
    public loginService: LoginService) { }

  ngOnInit(): void {
              //Smeta jer ispsiuje sefaultne vrednosti
    // this.forma.get("id")?.setValue(this.user?.id);
    // this.forma.get("username")?.setValue(this.user?.id);
    // this.forma.get("password")?.setValue(this.user?.id);
    // this.forma.get("confirmPassword")?.setValue(this.user?.id);
    // this.forma.get("firstName")?.setValue(this.user?.id);
    // this.forma.get("lastName")?.setValue(this.user?.id);
    // this.forma.get("email")?.setValue(this.user?.id);
    // this.forma.get("ucin")?.setValue(this.user?.id);
  
  }

  checkPasswords(pw: string, cpw: string) {
    this.isConfirmPasswordDirty = true;
    if (pw == cpw) {
      this.passwordsMatching = true;
      this.confirmPasswordClass = 'form-control is-valid';
    } else {
      this.passwordsMatching = false;
      this.confirmPasswordClass = 'form-control is-invalid';
    }
  }

  get passwordMatchError() {
    return (
      this.forma.getError('mismatch') && this.forma.get('confirmPassword')?.touched
    );
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }

  goBack(){
    this.router.navigate(['/users']);  
  }

  error_messages = {
    'username': [
      { type: 'required', message: 'Username is required!' },
    ],
    'password': [
      { type: 'required', message: 'Password is required!' },
      { type: 'minlength', message: 'Minimum password length is 8 characters!' },
      { type: 'maxlength', message: 'Maximum password length is 30 characters!' }
    ],

    'email': [
      { type: 'minlength', message: 'Minimum Email length is 6 characters!' },
      { type: 'maxlength', message: 'Maximum Email length is 40 characters!' },
      { type: 'required', message: 'Please enter a valid email address!' }
    ],

  }

}
