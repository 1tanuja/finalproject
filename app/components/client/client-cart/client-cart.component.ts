import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin, take } from 'rxjs';
import { Cart } from '../../model/cart.model';
import { FoodDeliveryService } from '../../service/fooddelivery.service';
import * as _ from "lodash";
import { Food } from '../../model/food.model';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-client-cart',
  templateUrl: './client-cart.component.html',
  styleUrls: ['./client-cart.component.css']
})
export class ClientCartComponent implements OnInit {
  cartList: Cart[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  user: any = {};
  quantity:any;


  constructor(
    private fService: FoodDeliveryService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.fService.isClientLoginPresent();
    this.getCartList();
    this.getCustomerDetail();
  }

  ngOnInit(): void {
  }
  getCartList(): void {
    this.fService.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("********", res);
        if (!!res && Array.isArray(res)) {
          const customerFilter = res.filter((item: Cart)=> item?.user?.id === parseInt(this.fService.getClientAuthorization()));
          console.log("customer filter::::::",customerFilter);
          this.cartList = customerFilter;
          this.cartListBackup =  _.cloneDeep(customerFilter);
          if (this.cartList.length > 0) {
            this.cartList.map((item: Cart) => {
              this.grandTotal += (item?.totalPrice);
            })
          }
        }
      }, err => {
        console.log("error");
      }

    );
  }
  getTotal(quantity: number = 0, totalPrice: number = 0): number {
    return quantity * totalPrice;
  }

  
  placeOrder(): void {
    let totalPrice: number = 0;
    const deleteCartReq:any[]=[];
    const foodItems: Array<Food> = [];
    this.cartList.forEach((item: Cart) => {
      foodItems.push(item?.food);
      totalPrice += (item?.totalPrice );
      deleteCartReq.push(this.fService.deleteCart(item?.cartId));
    });
    console.log('>>>>>>>>', totalPrice)
      const body: any = {
      totalPrice: totalPrice,
      orderStatus: "success",
      paymentStatus: "success",
      orderedDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
      user: this.user,
      foodName: "",
      food: foodItems
    };
    

    this.fService.placeOrder(this.user?.id,body).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>*', res);
      forkJoin(deleteCartReq).pipe(take(1)).subscribe();
      alert("Place order Sucessfully");
      this.router.navigate(["/client/order"]);
    })

  }

  placeOrderItem() {
    

  }

  getCustomerDetail(): void {
    const cid = this.fService.getClientAuthorization();
    this.fService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer*****", res);
        if (!!res && res?.id) {
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }

  deleteCart(cart:Cart, showAlert: boolean = true):void{
    this.fService.deleteCart(cart?.cartId).pipe(take(1)).subscribe(
      (res: any) => {
        if (showAlert) {
          alert("Product deleted sucessfully");
        }
       
        this.getCartList();
      }, err => {
        console.log("Err");
      }
    )
  }

  onIncreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    // const bac = Object.assign(this.cartListBackup);
    const indexBackup = this.cartListBackup.findIndex((item: Cart) => item.cartId === cart?.cartId);
    this.cartList[index].quantity = this.quantity;
    this.updateGrantTotal();
  }

  onDecreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const qty = cart.quantity - 1;
    if (qty === 0) {
      this.deleteCart(cart, false);
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  updateGrantTotal(): void {
    let total = 0;
    this.cartList.map((item: Cart) => {
      total+= (item?.totalPrice * item?.quantity);
     
    })
    this.grandTotal = total;
  }

}
