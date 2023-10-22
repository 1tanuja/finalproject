import { TestBed } from '@angular/core/testing';

import { FoodDeliveryService } from './fooddelivery.service';

describe('Service', () => {
  let service: FoodDeliveryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodDeliveryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
