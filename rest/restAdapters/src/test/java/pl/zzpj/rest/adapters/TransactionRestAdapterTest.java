package pl.zzpj.rest.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.zzpj.controller.TransactionUseCase;
import pl.zzpj.dto.AccountDto;
import pl.zzpj.exceptions.LoanNotAvailableException;
import pl.zzpj.exceptions.LoanNotAvailableRestException;
import pl.zzpj.model.Currency;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRestAdapterTest {

    @Mock
    private TransactionUseCase transactionUseCase;

    @InjectMocks
    private TransactionRestAdapter transactionRestAdapter;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void takeLoan() {
        try {
            transactionRestAdapter.takeLoan("fr", BigDecimal.ONE);
        } catch (LoanNotAvailableRestException e) {
            fail(e.getMessage());
        }

        try {
            verify(transactionUseCase).takeLoan("fr", BigDecimal.ONE);
        } catch (LoanNotAvailableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void takeLoanException() throws LoanNotAvailableException {
        doThrow(LoanNotAvailableException.class).when(transactionUseCase).takeLoan("fr", BigDecimal.ONE);
        assertThrows(LoanNotAvailableRestException.class, () -> transactionRestAdapter.takeLoan("fr", BigDecimal.ONE));
    }
}