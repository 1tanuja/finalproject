import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Food } from '../../model/food.model';
import { FoodDeliveryService } from '../../service/fooddelivery.service';

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css']
})
export class ClientHomeComponent implements OnInit {

  foodList: Array<Food> = [];
  quantity: any;
  user: any = {};
  getCategoryList: any[] = [];
  category: any = 100;
  allFoodList : Array<Food>= [];
  offset: number = 0;
  pageSize: number = 10; // How many item you want to display in your page.
  totalItem: number = 1;

  constructor(
    private fService: FoodDeliveryService,
    private router: Router,
    private snakcbar: MatSnackBar
  ) {
   this.fService.isClientLoginPresent();
    this.getFoodList(true);
    this.getCustomerDetail();
  }


  ngOnInit(): void {
    this.getCategoryList = this.fService.getCategoryList();
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

  getFoodList(isAllProduct: boolean = false): void {
    let food: any = this.fService.getAllProducts(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    if (!isAllProduct) {
      food = this.fService.getProductByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    }
    food.pipe(take(1)).subscribe((res: any) => {
      ;
      if (res && res?.food && Array.isArray(res?.food)) {
        this.foodList = res?.food;
        this.allFoodList = res?.food;
        this.totalItem = res?.totalProduct;
      }
    }, (err: any) => {
      console.log("Error");
    });
  }

  addToCart(food: Food): void {
    const element: any = document.getElementById(food?.foodId.toString());
 
    if (this.quantity === 0 || this.quantity<0) {
      alert("Qunatity should not be  zero or negative");
      return ;
    }
    
    
    const body: any = {
      quantity: this.quantity,
      mrpPrice: food?.price,
      food: food,
      user: this.user
    };
    console.log("add to cart", body);
    this.fService.addToCart(body, food?.foodId, this.user?.id,this.quantity).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
        if (!!res && res?.cartId) {
        alert("Item added sucessfully");
          this.getFoodList(true);
        }
      }, err => {
        console.log("Error");
      }
    )
  }

  getProductByCategory(ev:any): void {
    this.offset = 0;
    this.totalItem = 1;
    this.category = ev?.value;
    if (this.category === "100") {
      this.getFoodList(true);
    } else {
      this.getFoodList(false);
    }
  }

  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getFoodList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getFoodList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getFoodList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalItem / this.pageSize);
    this.offset = lastPage;
    this.getFoodList(this.category === 100 || this.category === "100");
  }

}



