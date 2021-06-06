import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../model/Account';
import { AccountService } from '../services/account.service';

@Component({
    selector: 'app-edit-user',
    templateUrl: './edit-user.component.html',
    styleUrls: ['./edit-user.component.less']
})
export class EditUserComponent implements OnInit {

    private readonly login: string;

    public account: Account;

    public currencies = [
        'PLN',
        'USD',
        'CHF',
        'EUR',
        'GBP',
    ];
    public accessLevels = [
        'CLIENT',
        'ADMIN',
    ];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private accountService: AccountService,) {
        this.login = (this.route.snapshot.paramMap.get('login') as string);
        this.account = {
            accessLevel: {
                id: '',
                level: ''
            },
            firstName: '',
            id: '',
            lastName: '',
            login: ''
        }
        this.getAccount(this.login);
    }

    getAccount(login: string) {
        this.accountService.getAccount(login).subscribe(
            (response: Account) => {
                this.account = response;
            }
        );
    }

    ngOnInit(): void {
    }

    editAccount() {
        this.accountService.editAccount(this.login, this.account).subscribe(
            () => this.router.navigate(['/accounts'])
        );
    }
}
