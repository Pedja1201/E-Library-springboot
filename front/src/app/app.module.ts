import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatStepperModule} from '@angular/material/stepper';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';


import { AuthInterceptor } from './interceptors/auth.interceptor';
import { AddLibrariesComponent } from './components/library/add-libraries/add-libraries.component';
import { EditLibrariesComponent } from './components/library/edit-libraries/edit-libraries.component';
import { AllLibrariesComponent } from './components/library/all-libraries/all-libraries.component';
import { AddBooksComponent } from './components/book/add-books/add-books.component';
import { EditBooksComponent } from './components/book/edit-books/edit-books.component';
import { AllBooksComponent } from './components/book/all-books/all-books.component';
import { AddRentsComponent } from './components/rent/add-rents/add-rents.component';
import { EditRentsComponent } from './components/rent/edit-rents/edit-rents.component';
import { AllRentsComponent } from './components/rent/all-rents/all-rents.component';
import { AddOrdersComponent } from './components/order/add-orders/add-orders.component';
import { EditOrdersComponent } from './components/order/edit-orders/edit-orders.component';
import { AllOrdersComponent } from './components/order/all-orders/all-orders.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { EditUserComponent } from './components/user/edit-user/edit-user.component';
import { AllUserComponent } from './components/user/all-user/all-user.component';
import { LoginComponent } from './components/auth/login/login.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { FormSignupComponent } from './components/auth/signup/form-signup/form-signup.component';
import { FormSignupCustomerComponent } from './components/auth/signup/form-signup-customer/form-signup-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    AddLibrariesComponent,
    EditLibrariesComponent,
    AllLibrariesComponent,
    AddBooksComponent,
    EditBooksComponent,
    AllBooksComponent,
    AddRentsComponent,
    EditRentsComponent,
    AllRentsComponent,
    AddOrdersComponent,
    EditOrdersComponent,
    AllOrdersComponent,
    AddUserComponent,
    EditUserComponent,
    AllUserComponent,
    LoginComponent,
    SignupComponent,
    FormSignupComponent,
    FormSignupCustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule,
    MatListModule,
    MatTableModule,
    MatTabsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
