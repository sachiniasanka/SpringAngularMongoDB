import { Component, OnInit } from '@angular/core';
import {Customer} from "../customer";
import {CustomerService} from "../customer.service";
import {subscribeOn} from "rxjs/operator/subscribeOn";

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {


  customer: Customer = new Customer();
  submitted = false;

  constructor(private customerService: CustomerService) {
  }

  ngOnInit() {

  }

  // searchCustomer(){
  //
  //   this.customer = [];
  //   this.customerService.search(this.searchCustomer().subscribe((data:Customer) =>{
  //     this.customer.push(data);
  //   }));

  // }
  // update() {



  update( customer:boolean) {
    this.customerService.updateCustomer(this.customer.id,
      { name: this.customer.name, age: this.customer.age })
      .subscribe(
      data => {
        console.log(data);
        this.customer = data as Customer;
      },
      error => console.log(error));
  }
  findById(){
  this.customerService.getCustomersList(this.customer.id)
    if(this.customer.id == )




  }

}
