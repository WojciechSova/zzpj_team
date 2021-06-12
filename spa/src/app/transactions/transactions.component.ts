import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TransactionService } from "../services/transaction.service";
import { Transaction } from "../model/Transaction";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.less']
})
export class TransactionsComponent implements OnInit {

    public transactions: Transaction[];

    constructor(private transactionService: TransactionService,
                private router: Router) {
        this.transactions = [];
        this.getTransactions();
    }

    ngOnInit(): void {
    }

    getTransactions(): void {
        this.transactionService.getTransactions().subscribe(
            (response: Transaction[]) => {
                this.transactions = response;
            }
        );
    }

}
