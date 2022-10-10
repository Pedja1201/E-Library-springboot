import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLibrariesComponent } from './add-libraries.component';

describe('AddLibrariesComponent', () => {
  let component: AddLibrariesComponent;
  let fixture: ComponentFixture<AddLibrariesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLibrariesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLibrariesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
