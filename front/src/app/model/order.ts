import { Book } from "./book";
import { Customer } from "./customer";

export interface OrderPage<Order> {
    content: Order[];
  }
export class Order {
    id: number;
    quantity : number;
    payment : string;
    currency : string;
    dateOrder: Date;
    customer : Customer[];
    book : Book[];


    constructor (){
        this.id = 0;
        this.quantity = 0;
        this.payment = '';
        this.currency = '';
        this.dateOrder = new Date();
        this.customer = [];
        this.book = [];
    }
}
