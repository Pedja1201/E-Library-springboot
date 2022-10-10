import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { AddBooksComponent } from './components/book/add-books/add-books.component';
import { AllBooksComponent } from './components/book/all-books/all-books.component';
import { EditBooksComponent } from './components/book/edit-books/edit-books.component';
import { AddLibrariesComponent } from './components/library/add-libraries/add-libraries.component';
import { AllLibrariesComponent } from './components/library/all-libraries/all-libraries.component';
import { EditLibrariesComponent } from './components/library/edit-libraries/edit-libraries.component';
import { AddOrdersComponent } from './components/order/add-orders/add-orders.component';
import { AllOrdersComponent } from './components/order/all-orders/all-orders.component';
import { EditOrdersComponent } from './components/order/edit-orders/edit-orders.component';
import { AddRentsComponent } from './components/rent/add-rents/add-rents.component';
import { AllRentsComponent } from './components/rent/all-rents/all-rents.component';
import { EditRentsComponent } from './components/rent/edit-rents/edit-rents.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { AllUserComponent } from './components/user/all-user/all-user.component';
import { EditUserComponent } from './components/user/edit-user/edit-user.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [

  {path : 'login', component : LoginComponent},
  {path : 'signup', component : SignupComponent}, 

  {path: 'users', component: AllUserComponent, canActivate:[AuthGuard]},
  {path : 'add-users', component : AddUserComponent},
  {path: 'edit-users/:_id', component: EditUserComponent, canActivate:[AuthGuard]},

  {path: 'libraries', component: AllLibrariesComponent, canActivate:[AuthGuard]},
  {path : 'add-library', component : AddLibrariesComponent},
  {path: 'edit-library/:_id', component: EditLibrariesComponent, canActivate:[AuthGuard]},

  {path: 'books', component: AllBooksComponent, canActivate:[AuthGuard]},
  {path : 'add-book', component : AddBooksComponent},
  {path: 'edit-book/:_id', component: EditBooksComponent, canActivate:[AuthGuard]},

  {path: 'rents', component: AllRentsComponent, canActivate:[AuthGuard]},
  {path : 'add-rent', component : AddRentsComponent},
  {path: 'edit-rent/:_id', component: EditRentsComponent, canActivate:[AuthGuard]},

  {path: 'orders', component: AllOrdersComponent, canActivate:[AuthGuard]},
  {path : 'add-order', component : AddOrdersComponent},
  {path: 'edit-order/:_id', component: EditOrdersComponent, canActivate:[AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
