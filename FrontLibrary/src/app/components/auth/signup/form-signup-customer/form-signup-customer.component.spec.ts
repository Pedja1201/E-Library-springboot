import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSignupCustomerComponent } from './form-signup-customer.component';

describe('FormSignupCustomerComponent', () => {
  let component: FormSignupCustomerComponent;
  let fixture: ComponentFixture<FormSignupCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormSignupCustomerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormSignupCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
