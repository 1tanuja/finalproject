import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';

import { FoodDeliveryService } from '../../service/fooddelivery.service';
import { Food } from '../../model/food.model';

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {

  productList: Array<Food> = [];
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList: Array<Food> = [];
  offset: number = 0;
  pageSize: number = 10; // How many item you want to display in your page.
  totalItem: number = 1;

  constructor(
    private fService: FoodDeliveryService,
    private router: Router
  ) {
    this.fService.isAdminLoginPresent();
    this.getProductList(true);
  }

  ngOnInit(): void {
    this.getCategoryList = this.fService.getCategoryList();
  }

  getProductList(isAllProduct: boolean = false): void {
    let food: any = this.fService.getAllProducts(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    if (!isAllProduct) {
      food = this.fService.getProductByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    }
    food.pipe(take(1)).subscribe((res: any) => {
      ;
      if (res && res?.food && Array.isArray(res?.food)) {
        this.productList = res?.food;
        this.allProductList = res?.food;
        this.totalItem = res?.totalFood;
      }
    }, (err: any) => {
      console.log("Error");
    });
  }

  delProduct(food: Food): void {
    this.fService.deleteProduct(food?.foodId).pipe(take(1)).subscribe(
      (res: any) => {
        alert("Product deleted sucessfully");
        this.getProductList(this.category === 100 || this.category === "100");
      }, err => {
        console.log("Error");
      }
    )
  }

  editProduct(food: Food): void {
    this.router.navigate(['/admin/addproduct'], {
      queryParams: {
        foodId: food?.foodId
      }
    });

  }

  getProductByCategory(ev:any): void {
    this.offset = 0;
    this.totalItem = 1;
    this.category = ev?.value;
    if (this.category === "100") {
      this.getProductList(true);
    } else {
      this.getProductList(false);
    }
  }

  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalItem / this.pageSize);
    this.offset = lastPage;
    this.getProductList(this.category === 100 || this.category === "100");
  }

}
