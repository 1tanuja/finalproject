import { Component, OnInit } from '@angular/core';
import { FoodDeliveryService } from '../../service/fooddelivery.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  userName: string = '';
  constructor(
    private fService: FoodDeliveryService
  ) {
    if (this.fService.getAdminName() !== null) {
      this.userName = this.fService.getAdminName();
    }
    this.fService.isAdminLoginPresent();
  }

  ngOnInit(): void {
  }

}
