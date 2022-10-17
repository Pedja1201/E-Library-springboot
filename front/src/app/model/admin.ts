import { User } from "./user";

export interface AdminPage<Admin> {
    content: Admin[];
  }
export class Admin extends User{
    override username: string;
    override password: string;
    firstName : string;
    lastName : string;
    email : string;
    ucin: string;


    constructor (){
        super();
        this.username = '';
        this.password = '';
        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.ucin = '';
    }
}
