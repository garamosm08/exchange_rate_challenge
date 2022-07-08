import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content?: string;
  exchangetypes?: [];
  currencies = [];
  accounts = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserAccounts().subscribe(
      data => {
        console.log(data)
        this.accounts = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    this.userService.getAllCurrencies().subscribe(
      data => {
        console.log(data)
        this.currencies = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    this.userService.getAllExchangeTypes().subscribe(
      data => {
        console.log(data)
        this.exchangetypes = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

 
}
