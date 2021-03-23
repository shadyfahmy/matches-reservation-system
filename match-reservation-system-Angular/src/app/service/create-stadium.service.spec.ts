import { TestBed } from '@angular/core/testing';

import { CreateStadiumService } from './create-stadium.service';

describe('CreateStadiumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateStadiumService = TestBed.get(CreateStadiumService);
    expect(service).toBeTruthy();
  });
});
