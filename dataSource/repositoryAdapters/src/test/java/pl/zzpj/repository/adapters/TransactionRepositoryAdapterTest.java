package pl.zzpj.repository.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.zzpj.model.AccessLevel;
import pl.zzpj.model.Account;
import pl.zzpj.model.Currency;
import pl.zzpj.repositories.AccountRepository;
import pl.zzpj.repositories.TransactionRepository;
import pl.zzpj.repository.mappers.AccountMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class TransactionRepositoryAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionRepositoryAdapter transactionRepositoryAdapter;

    @Spy
    Account from;

    @Spy
    Account to;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
}
