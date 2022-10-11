export interface UserPage<User> {
    content: User[];
  }
export class User { 
    id:number;
    username:string;
    password:string;


    constructor(){
        this.id=0,
        this.username='',
        this.password=''
    }
}
