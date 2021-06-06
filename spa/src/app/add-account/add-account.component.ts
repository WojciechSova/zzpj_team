import { Component, OnInit } from '@angular/core';
import {AccountService} from "../services/account.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-add-account',
    templateUrl: './add-account.component.html',
    styleUrls: ['./add-account.component.less']
})
export class AddAccountComponent implements OnInit {
    login = '';
    password = '';
    firstName = '';
    lastName = '';
    currency = 'PLN';
    accessLevel = 'CLIENT';
    currencies = [
        'PLN',
        'USD',
        'CHF',
        'EUR',
        'GBP',
    ];
    accessLevels = [
        'CLIENT',
        'ADMIN',
    ];

    constructor(private accountService: AccountService,
                private router: Router) {
    }

    ngOnInit(): void {
    }

    addAccount(): void {
        this.accountService.addAccounts({
            id: '0',
            login: this.login,
            password: this.password,
            firstName: this.firstName,
            lastName: this.lastName,
            currency: this.currency,
            accessLevel: {
                id: '0',
                level: this.accessLevel,
            },
        }).subscribe(() => {
            this.router.navigate(['/accounts'])
        })
    }
}
