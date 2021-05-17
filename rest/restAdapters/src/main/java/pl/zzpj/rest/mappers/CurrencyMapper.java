package pl.zzpj.rest.mappers;

import pl.zzpj.model.Currency;
import pl.zzpj.dto.CurrencyDto;

public class CurrencyMapper {

    public static Currency mapToCurrency(CurrencyDto currencyDto) {
        Currency currency;
        if (currencyDto.equals(CurrencyDto.USD)) {
            currency = Currency.USD;
        } else if (currencyDto.equals(CurrencyDto.EUR)) {
            currency = Currency.EUR;
        } else if (currencyDto.equals(CurrencyDto.CHF)) {
            currency = Currency.CHF;
        } else if (currencyDto.equals(CurrencyDto.GBP)) {
            currency = Currency.GBP;
        } else {
            currency = Currency.PLN;
        }
        return currency;
    }

    public static CurrencyDto mapToCurrencyDto(Currency currency) {
        CurrencyDto currencyDto;
        if (currency.equals(Currency.USD)) {
            currencyDto = CurrencyDto.USD;
        } else if (currency.equals(Currency.EUR)) {
            currencyDto = CurrencyDto.EUR;
        } else if (currency.equals(Currency.CHF)) {
            currencyDto = CurrencyDto.CHF;
        } else if (currency.equals(Currency.GBP)) {
            currencyDto = CurrencyDto.GBP;
        } else {
            currencyDto = CurrencyDto.PLN;
        }
        return currencyDto;
    }
}
