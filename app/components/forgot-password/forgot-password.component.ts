import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';

import { FoodDeliveryService } from '../service/fooddelivery.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email: string= '';
  isShowChangePassword: boolean = false;
  newPassword: string = '';
  user: any;
  constructor(
    private fService: FoodDeliveryService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const body = {
      email: this.email
    };
    this.fService.forgotPassword(body).pipe(take(1)).subscribe((res) => {
      if (!!res && res?.id) {
        this.user = res;
        this.isShowChangePassword = true;
      }
    }, err => {
      this.isShowChangePassword = false;
      alert("Customer not found . Please check email address.")
    });
  }

  onChangePassword(): void {
    this.user.password = this.newPassword;
    this.fService.changePassword(this.user?.id,this.newPassword).pipe(take(1)).subscribe((res) => {
      if (res && res.id) {
        alert("Password has been change successfully");
        this.route.navigate(["/client-login"]);
      }
    }, error => {
      alert("Error occured while change password.");
    });
  }

}
