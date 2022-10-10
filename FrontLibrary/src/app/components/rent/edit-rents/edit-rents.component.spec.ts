import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditRentsComponent } from './edit-rents.component';

describe('EditRentsComponent', () => {
  let component: EditRentsComponent;
  let fixture: ComponentFixture<EditRentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditRentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditRentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
