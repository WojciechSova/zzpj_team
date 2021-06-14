import { Component, OnInit } from '@angular/core';
import { Account } from "../model/Account";
import { AccountService } from "../services/account.service";
import {TransactionService} from "../services/transaction.service";

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.less']
})
export class AccountComponent implements OnInit {

    public account: Account = {
        accessLevel: {
            id: '',
            level: ''
        },
        active: false,
        firstName: "",
        id: "",
        lastName: "",
        login: ""
    };
    maxLoan: number =10;

    constructor(private accountService: AccountService,
                private transactionService: TransactionService) {
        this.getAccount();
        this.getMaxLoan();
    }

    ngOnInit(): void {
    }

    getAccount(): void {
        this.accountService.getOwnAccount().subscribe(
            (response: Account) => {
                this.account = response;
            }
        );
    }

    deposit(value: string) : any {
        this.transactionService.deposit(value).subscribe();
    }

    withdraw(value: string) : any {
        this.transactionService.withdraw(value).subscribe();
    }

    transfer(value: string, accountNumber: string) : any {
        this.transactionService.transfer(value, accountNumber).subscribe();
    }

    getMaxLoan() : any {
        //TODO
        this.maxLoan = 20;
    }

    takeLoan(value: string) : any {
        this.transactionService.takeLoan(value).subscribe();
    }
}
