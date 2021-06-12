import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class TransactionService {

    private readonly url: string;

    constructor(private http: HttpClient) {
        this.url = 'http://localhost:8080';
    }

    getTransactions(): any {
        return this.http.get(this.url + '/transactions',{
            observe: 'body',
            responseType: 'json'
        });
    }
}
