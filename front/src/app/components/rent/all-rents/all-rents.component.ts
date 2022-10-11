import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Rent } from 'src/app/model/rent';
import { RentsService } from 'src/app/services/rents/rents.service';

@Component({
  selector: 'app-all-rents',
  templateUrl: './all-rents.component.html',
  styleUrls: ['./all-rents.component.css']
})
export class AllRentsComponent implements OnInit {

  rents : Rent[] = [];

  constructor(
    private service : RentsService,
     private router : Router) {
    
   }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.rents = value.content }
    });
  }


  delete = (rent: Rent) => {
    this.service.delete(rent)
      .subscribe(resp => this.getAll());
  }

  edit(rent:Rent){
    this.router.navigate(['/edit-rent', rent.id])
  }

  addForm(){
    this.router.navigate(['/add-rent'])
  }


}
