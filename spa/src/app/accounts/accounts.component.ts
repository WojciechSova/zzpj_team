import {Component, OnInit} from '@angular/core';
import {AccountService} from '../services/account.service';
import {Account} from "../model/Account";

@Component({
    selector: 'app-accounts',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.less']
})
export class AccountsComponent implements OnInit {

    public accounts: Account[];

    constructor(private accountService: AccountService) {
        this.accounts = [];
        this.getAccounts();
    }

    ngOnInit(): void {
    }

    getAccounts(): Account[] | null {
        this.accountService.getAccounts().subscribe(
            (response: Account[]) => {
                this.accounts = response;
                console.log(this.accounts);
            }
        );

        return null;
    }
}
