import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAdminsComponent } from './edit-admins.component';

describe('EditAdminsComponent', () => {
  let component: EditAdminsComponent;
  let fixture: ComponentFixture<EditAdminsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAdminsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAdminsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
