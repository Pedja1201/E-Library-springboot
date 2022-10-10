import { TestBed } from '@angular/core/testing';

import { RentsService } from './rents.service';

describe('RentsService', () => {
  let service: RentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
