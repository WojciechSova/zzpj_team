package pl.zzpj.repository.mappers;

import pl.zzpj.model.Currency;
import pl.zzpj.entities.CurrencyEnt;

public class CurrencyMapper {

    public static Currency mapToCurrency(CurrencyEnt currencyEnt) {
        Currency currency;
        if (currencyEnt.equals(CurrencyEnt.USD)) {
            currency = Currency.USD;
        } else if (currencyEnt.equals(CurrencyEnt.EUR)) {
            currency = Currency.EUR;
        } else if (currencyEnt.equals(CurrencyEnt.CHF)) {
            currency = Currency.CHF;
        } else if (currencyEnt.equals(CurrencyEnt.GBP)) {
            currency = Currency.GBP;
        } else {
            currency = Currency.PLN;
        }
        return currency;
    }

    public static CurrencyEnt mapToCurrencyEnt(Currency currency) {
        CurrencyEnt currencyEnt;
        if (currency.equals(Currency.USD)) {
            currencyEnt = CurrencyEnt.USD;
        } else if (currency.equals(Currency.EUR)) {
            currencyEnt = CurrencyEnt.EUR;
        } else if (currency.equals(Currency.CHF)) {
            currencyEnt = CurrencyEnt.CHF;
        } else if (currency.equals(Currency.GBP)) {
            currencyEnt = CurrencyEnt.GBP;
        } else {
            currencyEnt = CurrencyEnt.PLN;
        }
        return currencyEnt;
    }
}
