import { TestBed } from '@angular/core/testing';

import { AdminstratorService } from './adminstrator.service';

describe('AdminstratorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminstratorService = TestBed.get(AdminstratorService);
    expect(service).toBeTruthy();
  });
});
