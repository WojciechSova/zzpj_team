package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.Currency;
import pl.zzpj.dto.CurrencyDto;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMapperTest {

    @Test
    void mapToCurrency() {
        Currency eur = Currency.EUR;
        assertEquals(CurrencyDto.EUR, CurrencyMapper.mapToCurrencyDto(eur));

        Currency chf = Currency.CHF;
        assertEquals(CurrencyDto.CHF, CurrencyMapper.mapToCurrencyDto(chf));

        Currency gbp = Currency.GBP;
        assertEquals(CurrencyDto.GBP, CurrencyMapper.mapToCurrencyDto(gbp));

        Currency pln = Currency.PLN;
        assertEquals(CurrencyDto.PLN, CurrencyMapper.mapToCurrencyDto(pln));

        Currency usd = Currency.USD;
        assertEquals(CurrencyDto.USD, CurrencyMapper.mapToCurrencyDto(usd));

    }

    @Test
    void mapToCurrencyDto() {
        CurrencyDto eur = CurrencyDto.EUR;
        assertEquals(Currency.EUR, CurrencyMapper.mapToCurrency(eur));

        CurrencyDto chf = CurrencyDto.CHF;
        assertEquals(Currency.CHF, CurrencyMapper.mapToCurrency(chf));

        CurrencyDto gbp = CurrencyDto.GBP;
        assertEquals(Currency.GBP, CurrencyMapper.mapToCurrency(gbp));

        CurrencyDto pln = CurrencyDto.PLN;
        assertEquals(Currency.PLN, CurrencyMapper.mapToCurrency(pln));

        CurrencyDto usd = CurrencyDto.USD;
        assertEquals(Currency.USD, CurrencyMapper.mapToCurrency(usd));
    }
}
