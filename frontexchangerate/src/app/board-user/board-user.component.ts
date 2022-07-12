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
  currencySelected = null;
  exchangeActionSelected = 1;
  amount = null;
  dropdownAccounts = [];
  sourceAccounts = [];
  targetAccounts = [];
  sourceAccountSelected = null;
  targetAccountSelected = null;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserAccounts().subscribe(
      data => {
        console.log(data)
        this.accounts = data;

        for(let i in this.accounts){
            this.dropdownAccounts.push({"id": this.accounts[i].id, "label": this.accounts[i].code + " - " + this.accounts[i].currencyCode});
        }
        console.log(this.dropdownAccounts);

        this.sourceAccounts = this.dropdownAccounts.filter(account => account.label.indexOf("PEN") != -1);
        console.log(this.sourceAccounts);
        this.sourceAccountSelected = this.sourceAccounts[0].id;

        this.targetAccounts = this.dropdownAccounts.filter(account => account.label.indexOf("PEN") == -1);
        console.log(this.targetAccounts);
        this.targetAccountSelected = this.targetAccounts[0].id;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

    this.userService.getAllCurrencies().subscribe(
      data => {
        console.log(data)
        this.currencies = data;
        this.currencySelected = data[0].id;
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

  onOptionsSelected(value:string): void{
    this.currencySelected = parseInt(value);
    console.log(this.currencySelected);

    let tempSourceAccounts = [...this.sourceAccounts];
    let tempTargetAccounts = [...this.targetAccounts];

    this.sourceAccounts = tempTargetAccounts;
    this.targetAccounts = tempSourceAccounts;

    this.sourceAccountSelected = this.sourceAccounts[0].id;
    this.targetAccountSelected = this.targetAccounts[0].id;
  }

  changeAmount(e) {
    console.log(e.target.value);
    this.amount = parseFloat(e.target.value);
  }

  changeExchangeAction(e) {
    this.exchangeActionSelected = parseInt(e.target.value);
    console.log(this.exchangeActionSelected);
    let tempSourceAccounts = [...this.sourceAccounts];
    let tempTargetAccounts = [...this.targetAccounts];

    this.sourceAccounts = tempTargetAccounts;
    this.targetAccounts = tempSourceAccounts;

    this.sourceAccountSelected = this.sourceAccounts[0].id;
    this.targetAccountSelected = this.targetAccounts[0].id;
  }

  onSourceAccountSelected(value:string): void{
    this.sourceAccountSelected = parseInt(value);
    console.log("the selected value is " + this.sourceAccountSelected);
  }

  onTargetAccountSelected(value:string): void{
    this.targetAccountSelected = parseInt(value);
    console.log("the selected value is " + this.targetAccountSelected);
  }

  doProcess(e) {
    console.log("click");
    
    let tempSourceAccount = this.accounts.find(a => a.id === this.sourceAccountSelected);
    if(typeof tempSourceAccount === 'undefined'){
      return;
    }
    let sourceCurrency = this.currencies.find(c => c.codigo === tempSourceAccount.currencyCode);
    if(typeof sourceCurrency === 'undefined'){
      return;
    }

    let tempTargetAccount = this.accounts.find(a => a.id === this.targetAccountSelected);
    if(typeof tempTargetAccount === 'undefined'){
      return;
    }
    let targetCurrency = this.currencies.find(c => c.codigo === tempTargetAccount.currencyCode);
    if(typeof targetCurrency === 'undefined'){
      return;
    }

    let requestExchangeRate = {
      "sourceAccountId": this.sourceAccountSelected,
      "sourceCurrencyId": sourceCurrency.id,
      "targetAccountId": this.targetAccountSelected,
      "targetCurrencyId": targetCurrency.id,
      "amount": this.amount
    };

    console.log(requestExchangeRate);

    this.userService.doExchangeRate(requestExchangeRate).subscribe(
      data => {
        console.log(data.body)

        this.userService.getUserAccounts().subscribe(
          data => {
            console.log(data)
            this.accounts = data;
          },
          err => {
            this.content = JSON.parse(err.error).message;
          }
        );
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
