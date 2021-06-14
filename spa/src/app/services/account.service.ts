import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";
import {Account} from "../model/Account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

    private readonly url: string;

    constructor(private http: HttpClient,
                private cookieService: CookieService,
                private router: Router) {
        this.url = 'https://zzpj-team.herokuapp.com';
    }

    getAccounts(): any {
        return this.http.get(this.url + '/accounts',{
            observe: 'body',
            responseType: 'json'
        });
    }

    getOwnAccount(): any {
        return this.http.get(this.url + '/accounts/own',{
            observe: 'body',
            responseType: 'json'
        });
    }

    addAccounts(acc: Account): any {
        return this.http.post(this.url + '/accounts/add', acc);
    }

    getAccount(login: string): any {
        return this.http.get(this.url + `/accounts/${login}`,{
            observe: 'body',
            responseType: 'json'
        });
    }

    editAccount(login: string, account: Account) {
        return this.http.put(this.url + `/accounts/edit/${login}`,account);
    }

    blockAccount(login: string) {
        return this.http.put(this.url + `/accounts/block/${login}`, null);
    }

    unblockAccount(login: string) {
        return this.http.put(this.url + `/accounts/unblock/${login}`, null);
    }
}
