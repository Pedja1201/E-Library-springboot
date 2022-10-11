import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Library } from 'src/app/model/library';
import { LibrariesService } from 'src/app/services/library/libraries.service';

@Component({
  selector: 'app-all-libraries',
  templateUrl: './all-libraries.component.html',
  styleUrls: ['./all-libraries.component.css']
})
export class AllLibrariesComponent implements OnInit {
  libraries : Library[] = [];
  parameters : Library = new Library();

  constructor(
    private service : LibrariesService,
    public snackBar:MatSnackBar,
     private router : Router) {
    
   }

   ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe({
      next: (value: any) => { this.libraries = value.content }
    });
  }


  delete = (lib: Library) => {
    this.service.delete(lib)
      .subscribe(resp => this.getAll());
  }

  edit(library:Library){
    this.router.navigate(['/edit-library', library.id])
  }

  addForm(){
    this.router.navigate(['/add-library'])
  }


}
