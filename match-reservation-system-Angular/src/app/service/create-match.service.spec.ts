import { TestBed } from '@angular/core/testing';

import { CreateMatchService } from './create-match.service';

describe('CreateMatchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateMatchService = TestBed.get(CreateMatchService);
    expect(service).toBeTruthy();
  });
});
