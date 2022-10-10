import { Library } from "./library";

export class Book {
    id: number;
    name : string;
    author : string;
    category : string;
    price: number;
    status: boolean;
    library: Library[]


    constructor (){
        this.id =0;
        this.name = '';
        this.author = '';
        this.category = '';
        this.price = 0;
        this.status = false
        this.library = []
    }
}
