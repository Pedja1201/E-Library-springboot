export interface CustomerPage<Customer> {
    content: Customer[];
  }
export class Customer {
    id: number;
    firstName : string;
    lastName : string;
    dateOfBirth: Date;
    email : string;
    phoneNumber: string;
    place: string;
    address: string;


    constructor (){
        this.id = 0;
        this.firstName = '';
        this.lastName = '';
        this.dateOfBirth = new Date();
        this.email = '';
        this.phoneNumber = '';
        this.place = '';
        this.address = '';
    }
}
