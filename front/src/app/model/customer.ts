import { User } from "./user";

export interface CustomerPage<Customer> {
    content: Customer[];
  }
export class Customer extends User{
    override username: string;
    override password: string;
    firstName : string;
    lastName : string;
    dateOfBirth: Date;
    email : string;
    phoneNumber: string;
    place: string;
    address: string;


    constructor (){
        super();
        this.username = '';
        this.password = '';
        this.firstName = '';
        this.lastName = '';
        this.dateOfBirth = new Date();
        this.email = '';
        this.phoneNumber = '';
        this.place = '';
        this.address = '';
    }
}
