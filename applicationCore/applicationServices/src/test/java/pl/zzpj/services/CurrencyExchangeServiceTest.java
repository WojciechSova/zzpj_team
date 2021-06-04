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
        assertNotEquals(BigDecimal.valueOf(1.0),
                CurrencyExchangeService.exchangeFromTo(Currency.EUR, Currency.PLN));
        assertEquals(BigDecimal.valueOf(1.0),
                CurrencyExchangeService.exchangeFromTo(Currency.EUR, Currency.EUR));
    }
}