import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLibrariesComponent } from './edit-libraries.component';

describe('EditLibrariesComponent', () => {
  let component: EditLibrariesComponent;
  let fixture: ComponentFixture<EditLibrariesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditLibrariesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditLibrariesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
