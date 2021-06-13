import { Component, OnInit } from '@angular/core';
import { Account } from "../model/Account";
import { AccountService } from "../services/account.service";

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

    constructor(private accountService: AccountService) {
        this.getAccount();
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
}
