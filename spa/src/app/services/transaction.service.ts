import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class TransactionService {

    private readonly url: string;

    constructor(private http: HttpClient) {
        this.url = 'https://zzpj-team.herokuapp.com';
    }

    getTransactions(): any {
        return this.http.get(this.url + '/transactions', {
            observe: 'body',
            responseType: 'json'
        });
    }

    deposit(value: string): any {
        return this.http.post(this.url + '/transactions/deposit', value);
    }

    withdraw(value: string): any {
        return this.http.post(this.url + '/transactions/withdraw', value);
    }

    transfer(value: string, accountNumber: string): any {
        return this.http.post(this.url + '/transactions/transfer/' + accountNumber, value);
    }

    getMaxLoan(): any {
        return this.http.get(this.url + '/transactions/maxLoan');
    }

    takeLoan(value: string): any {
        return this.http.post(this.url + '/transactions/loan', value);
    }

    payBackLoan(value: string): any {
        return this.http.post(this.url + '/transactions/payBackLoan', value);
    }
}
