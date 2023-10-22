import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { FoodDeliveryService } from '../../service/fooddelivery.service';

@Component({
  selector: 'app-client-login-page',
  templateUrl: './client-login-page.component.html',
  styleUrls: ['./client-login-page.component.css']
})
export class ClientLoginPageComponent implements OnInit {

  email: string = "";
  password: string = "";
  clientLoginForm = new FormGroup({});

  constructor(
    private router: Router,
    private fservice:FoodDeliveryService,
    private fb: FormBuilder

  ) {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
    this.clientLoginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(pattern)]],
      password: [null, Validators.compose([Validators.required, Validators.minLength(8)])]
    });

  }

  ngOnInit(): void {
  }

  
  signIn(): void {

    const body = {
      "email": this.clientLoginForm.controls['email'].value,
      "password": this.clientLoginForm.controls['password'].value
    }
    console.log("=======>",body);
    this.fservice.clientSignIn(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.id){
       // alert("Login sucessful");
        this.fservice.storeClientAuthorization(res?.id);
        let userName = '';
        if (res?.firstname) {
          userName+=res?.firstname;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastname;
        }
        this.fservice.storeClientUserName(userName);
        this.router.navigate(['/client/home']);
       
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong in login!!pl try again");
    })

  }

  routeToNewUser(): void {
    this.router.navigate(["/client-register"]);
  }

  routeToForgotPassword(): void {
    this.router.navigate(["/forgot-password"]);
  }
}
