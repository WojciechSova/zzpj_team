package pl.zzpj.services;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.Currency;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyExchangeServiceTest {

    @Test
    void exchangeFromTo() throws Exception {
        CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
        assertNotEquals(BigDecimal.valueOf(1.0),
                currencyExchangeService.exchangeFromTo(Currency.EUR, Currency.PLN));
        assertEquals(BigDecimal.valueOf(1.0),
                currencyExchangeService.exchangeFromTo(Currency.EUR, Currency.EUR));
    }
}
