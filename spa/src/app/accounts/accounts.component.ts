import {Component, OnInit} from '@angular/core';
import {AccountService} from '../services/account.service';
import {Account} from "../model/Account";
import {Router} from "@angular/router";

@Component({
    selector: 'app-accounts',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.less']
})
export class AccountsComponent implements OnInit {

    public accounts: Account[];

    constructor(private accountService: AccountService,
                private router: Router) {
        this.accounts = [];
        this.getAccounts();
    }

    ngOnInit(): void {
    }

    getAccounts(): void {
        this.accountService.getAccounts().subscribe(
            (response: Account[]) => {
                this.accounts = response;
            }
        );
    }

    remove(login: string) : void {

    }

    edit(login: string): void {
        this.router.navigate([`/accounts/edit/${login}`]);
    }
}
