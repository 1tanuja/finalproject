import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FoodDeliveryService {
  url: string = 'http://localhost:8080';

  category: any = [{
    name: "BIRYANI" , value: 0,
  }, {
    name: "PIZZA", value: 1,
  }, {
    name: "NORTH_INDIAN", value: 2
  }, {
    name: "CHINESE", value:  3
  }, {
    name: "SOUTH_INDIAN", value:  4
  }, {
    name: "THALI", value:  5
  },{
  name: "CHICKEN", value:  6
  },{
    name: "OTHERS", value:  7
    }
];

  constructor(
    
    private http: HttpClient,
    private router: Router

  ) { }
  
  /* Client Registeration */
  signUp(body: any): Observable<any> {
    return this.http.post(this.url + "/api/users/register", body);
  }
  //client login
  clientSignIn(body: any): Observable<any> {
    return this.http.post(this.url + "/api/users/login", body);
  }
//once we logged in that time we are storing client id into token 
storeClientAuthorization(token: string): void {
  localStorage.setItem("token", token);
}

getClientAuthorization(): any {
  const token = localStorage.getItem("token");
  return token; 
}

storeClientUserName(name: string): void {
  localStorage.setItem("userName", name);
}

getClientName(): any {
  const name = localStorage.getItem("userName");
  return name;
}

clientLogout(): void {
  localStorage.clear();
  this.router.navigate(['']);
}
//admin login
adminSignIn(body: any): Observable<any> {
  return this.http.post(this.url + "/api/admin/login", body);
}
storeAdminAuthorization(token: string): void {
  localStorage.setItem("admin", token);
}
getAdminAuthorization(): any {
  const token = localStorage.getItem("admin");
  return token; 
}

storeAdminUserName(name: string): void {
  localStorage.setItem("adminName", name);
}

getAdminName(): any {
  const name = localStorage.getItem("adminName");
  return name;
}

adminLogout(): void {
  localStorage.clear();
  this.router.navigate(['/']);
}
//product controller
addProduct(body: any): Observable<any> {
  return this.http.post(this.url + "/api/food/addFood", body);
}

getProductlist():Observable<any> {
  return this.http.get(this.url + "/api/food/getFoods");
}

deleteProduct(id :any):Observable<any> {
  //return this.http.delete(this.url + "/api/products/" +id);
  //secondway
  return this.http.delete(`${this.url}/api/food/delete/${id}`);
}

getProductById(id:any):Observable<any> {
  return this.http.get(this.url + "/api/food/getFoodById/"+id);
}

editProduct(body: any,id:any): Observable<any> {
  return this.http.put(this.url + "/api/food/update/"+id, body);
}
//cart 
addToCart(body: any,pid:any,cid:any,qyantity:any):Observable<any>{
  return this.http.post(this.url+"/api/cart/"+cid+"/"+pid+"/"+qyantity,body);
}

getCustomerById(id:any):Observable<any> {
  return this.http.get(this.url + "/api/users/getUser/"+id);
}

cartList():Observable<any>{
  return this.http.get(this.url+"/api/cart/getAll");
}
deleteCart(id :any):Observable<any> {
  return this.http.delete(`${this.url}/api/cart/delete/${id}`);
}
//order
placeOrder(cid:any,body:any):Observable<any> {
  return this.http.post(this.url + "/api/order/addOrder/"+cid, body);
}

getAllorderList():Observable<any>{
  return this.http.get(this.url+"/api/order/getAllOrder");
}


orderList(id:any):Observable<any>{
  return this.http.get(this.url+"/api/order/get/"+id);
}

getCategoryList(): any {
  return this.category;
}
addPayment(body:any,orderid:any,cid:any):Observable<any> {
  return this.http.post(this.url + "/api/payment/"+orderid+"/"+cid, body);
}

isClientLoginPresent(): void {
  if (this.getClientAuthorization() === null) {
    this.router.navigate(['/client-login']);
  }
}

isAdminLoginPresent(): void {
  if (this.getAdminAuthorization() === null) {
    this.router.navigate(['/admin-login']);
  }
}

forgotPassword(body: any):Observable<any> {
  return this.http.post(this.url + "/api/users/forgotpassword", body);
}

updateCustomerInformation(body: any):Observable<any> {
  return this.http.put(this.url + "/api/users/update/"+body?.id, body);
}

changePassword(uid: any,password:any):Observable<any> {
  return this.http.post(this.url + "/api/users/"+uid+"/"+password,{});
}

getProductByCategory(cid: any, offset: any, limit: any):Observable<any>{
  return this.http.get(this.url+"/api/food/" + cid + "/"+ offset + "/" + limit);
}

getAllProducts(offset: any, limit: any):Observable<any>{
  return this.http.get(this.url+"/api/food/" + offset + "/" + limit);
}

placeOrderItem(cid:any, body:any):Observable<any>{
  return this.http.put(`${this.url}/api/order/addOrder/${cid}`, body);
}

}
