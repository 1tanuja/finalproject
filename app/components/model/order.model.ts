import { Food } from "./food.model";

export interface Order{
    orderId : number;
    orderStatus:string;
    orderedDate:string;
    paymentStatus:string;
    quantity : number;
    totalPrice: number;	
    foodName: string;
    image: string;
    food: Array<Food>
    user:any;
}