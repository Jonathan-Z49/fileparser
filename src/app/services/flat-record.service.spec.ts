import { TestBed } from '@angular/core/testing';

import { FlatRecordService } from './flat-record.service';

describe('FlatRecordService', () => {
  let service: FlatRecordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlatRecordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
