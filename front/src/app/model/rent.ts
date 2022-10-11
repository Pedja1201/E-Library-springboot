import { Book } from "./book";
import { Customer } from "./customer";

export interface RentPage<Rent> {
    content: Rent[];
  }
export class Rent {
    id: number;
    quantity : number;
    payment : string;
    currency : string;
    rentalOrder: Date;
    dateOrder: Date;
    customer : Customer[];
    book : Book[];


    constructor (){
        this.id = 0;
        this.quantity = 0;
        this.payment = '';
        this.currency = '';
        this.rentalOrder = new Date();
        this.dateOrder = new Date();
        this.customer = [];
        this.book = [];
    }
}
