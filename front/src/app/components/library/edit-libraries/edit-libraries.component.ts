import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Library } from 'src/app/model/library';
import { LoginService } from 'src/app/services/auth/login.service';
import { LibrariesService } from 'src/app/services/library/libraries.service';

@Component({
  selector: 'app-edit-libraries',
  templateUrl: './edit-libraries.component.html',
  styleUrls: ['./edit-libraries.component.css']
})
export class EditLibrariesComponent implements OnInit {
  library : Library = new Library();
  errorMessage : string = '';

  constructor(
    private service: LibrariesService,
    private route: ActivatedRoute,
    private router: Router,
    public loginService : LoginService) { }


  ngOnInit(): void {
    let libraryId = Number(this.route.snapshot.paramMap.get("id"));
    this.service.getOne(libraryId).subscribe((value: any) => {
      this.library = value;
    })
  }

  update(library: Library) {
    if (library.id !== undefined) {
      this.service.update(library.id, library).subscribe({next: () => {
        this.service.getAll();
        this.router.navigate(['/libraries']);
      },
      error: (error) => {this.errorMessage = error.error}});
    }
  }

  goBack(){
    this.router.navigate(['/libraries']);
    // this.location.back();  
  }


}
