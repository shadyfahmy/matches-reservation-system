import { TestBed } from '@angular/core/testing';

import { EditMatchService } from './edit-match.service';

describe('EditMatchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditMatchService = TestBed.get(EditMatchService);
    expect(service).toBeTruthy();
  });
});
