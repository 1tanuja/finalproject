import { Food } from "./food.model";

export interface Cart{
    
    cartId : number;
    totalPrice : number;
    quantity : number;
    user : any;
    food: Food
}