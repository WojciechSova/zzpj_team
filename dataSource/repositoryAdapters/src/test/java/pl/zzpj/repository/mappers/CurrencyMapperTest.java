package pl.zzpj.repository.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.entities.CurrencyEnt;
import pl.zzpj.model.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMapperTest {

    @Test
    void mapToCurrency() {
        Currency eur = Currency.EUR;
        assertEquals(CurrencyEnt.EUR, CurrencyMapper.mapToCurrencyEnt(eur));

        Currency chf = Currency.CHF;
        assertEquals(CurrencyEnt.CHF, CurrencyMapper.mapToCurrencyEnt(chf));

        Currency gbp = Currency.GBP;
        assertEquals(CurrencyEnt.GBP, CurrencyMapper.mapToCurrencyEnt(gbp));

        Currency pln = Currency.PLN;
        assertEquals(CurrencyEnt.PLN, CurrencyMapper.mapToCurrencyEnt(pln));

        Currency usd = Currency.USD;
        assertEquals(CurrencyEnt.USD, CurrencyMapper.mapToCurrencyEnt(usd));
    }

    @Test
    void mapToCurrencyEnt() {
        CurrencyEnt eur = CurrencyEnt.EUR;
        assertEquals(Currency.EUR, CurrencyMapper.mapToCurrency(eur));

        CurrencyEnt chf = CurrencyEnt.CHF;
        assertEquals(Currency.CHF, CurrencyMapper.mapToCurrency(chf));

        CurrencyEnt gbp = CurrencyEnt.GBP;
        assertEquals(Currency.GBP, CurrencyMapper.mapToCurrency(gbp));

        CurrencyEnt pln = CurrencyEnt.PLN;
        assertEquals(Currency.PLN, CurrencyMapper.mapToCurrency(pln));

        CurrencyEnt usd = CurrencyEnt.USD;
        assertEquals(Currency.USD, CurrencyMapper.mapToCurrency(usd));
    }
}