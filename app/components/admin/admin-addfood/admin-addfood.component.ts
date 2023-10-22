import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';

import { FoodDeliveryService } from '../../service/fooddelivery.service';
import { Food } from '../../model/food.model';


@Component({
  selector: 'app-admin-addfood',
  templateUrl: './admin-addfood.component.html',
  styleUrls: ['./admin-addfood.component.css']
})
export class AdminAddFoodComponent implements OnInit {

  foodname: string = '';
  image: string = '';
  description: string = '';
  price: number = 0;
  isEdit: boolean = false;
  foodId: any;
  getCategoryList: any[] = [];
  category: number = 0;
  

  constructor(

    private fService: FoodDeliveryService,
    private router: Router,
    private activateRouter: ActivatedRoute


  ) {
    this.activateRouter.queryParams.subscribe((params: any) => {
      if (params?.id) {
        this.isEdit = true;
        this.fService.getProductById(params?.id).pipe(take(1)).subscribe((res:any)=> {
          if(!!res && res?.foodId){
            const food :Food=res;
            console.log('>>>>', food);
            this.foodname= food?.foodName;
            this.description=food?.description;
            this.image=food?.imageUrl;
            this.price=food?.price;           
            this.foodId=food?.foodId;
            const categoryName = this.getCategoryList.find((cate: any) => cate?.name.toString() === food?.category)?.value;
            this.category = categoryName;
            
          }
          console.log(res);
        });
      }

    })
  }
  ngOnInit(): void {
    this.fService.isAdminLoginPresent();
    this.getCategoryList = this.fService.getCategoryList();
  }

  onAddProduct(): void {
   
    if (this.foodname === '') {
      alert("Food name is required");
      return;
    }
    if (this.description === '') {
      alert("description  is required");
      return;
    }

    if (this.image === '') {
      alert("Image should not be blank");
      return;
    }
    console.log("******MRP price",this.price);
    if (this.price === 0 || this.price===null||this.price<0) {
      alert("MRP Price should not be zero/blank/negative");
      return;
    }
   
    
 

    const body: any = {
      foodName: this.foodname,
      imageUrl: this.image,
      description: this.description,
      price: this.price,
      category: this.category,
    }
    if(this.isEdit){
      console.log("=======>", body);
    this.fService.editProduct(body,this.foodId).pipe(take(1)).subscribe((res: any) => {
      console.log("*****", res);
      if (res && res?.productId) {
        alert("Product updated sucessfully");
        this.router.navigate(["/admin/listproduct"]);
      }
    }, err => {
      console.log("Error  ", err);
      alert("Something going wrong!!pl try again");
    })
    }else{
      console.log("=======>", body);
      this.fService.addProduct(body).pipe(take(1)).subscribe((res: any) => {
        console.log("*****", res);
        if (res && res?.foodId) {
          alert("Product added sucessfully");
          this.router.navigate(["/admin/listproduct"]);
        }
      }, err => {
        console.log("Error  ", err);
        alert("Something going wrong!!pl try again");
      })
    }

    

  }
}









