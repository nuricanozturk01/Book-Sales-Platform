import { TestBed } from '@angular/core/testing';

import { LogServiceService } from './log-service.service';

describe('LogServiceService', () => {
  let service: LogServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LogServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
