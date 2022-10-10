import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRentsComponent } from './add-rents.component';

describe('AddRentsComponent', () => {
  let component: AddRentsComponent;
  let fixture: ComponentFixture<AddRentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddRentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddRentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
