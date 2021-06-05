import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

    private readonly url: string;

    constructor(private http: HttpClient,
                private cookieService: CookieService,
                private router: Router) {
        this.url = 'http://localhost:8080';
    }

    getAccounts(): any {
        return this.http.get(this.url + '/accounts',{
            observe: 'body',
            responseType: 'json'
        });
    }
}
