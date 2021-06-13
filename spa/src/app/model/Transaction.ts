import { Account } from "./Account";

export interface Transaction {
    from: Account;
    fromCurrency: string;
    to: Account;
    toCurrency: string;
    date: Date;
    amount: number;
    rate: number;
    isLoan: boolean;
}
