package itau.desafio_api_itau.services;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.models.Statistic;
import itau.desafio_api_itau.models.Transaction;
import itau.desafio_api_itau.util.validators.ValidationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private ITransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    @Test
    void addTransaction_ValidDTO_CallsRepositoryAdd() {
        TransactionRequestDTO validDTO = new TransactionRequestDTO(100.0, OffsetDateTime.now().minusSeconds(30));
        ValidationResult result = service.addTransaction(validDTO);

        assertTrue(result.isValid());
        verify(repository).addTransaction(validDTO);
    }

    @Test
    void addTransaction_InvalidDTO_DoesNotCallRepositoryAdd() {
        TransactionRequestDTO invalidDTO = new TransactionRequestDTO(-50.0, OffsetDateTime.now().plusMinutes(1));
        ValidationResult result = service.addTransaction(invalidDTO);

        assertFalse(result.isValid());
        verify(repository, never()).addTransaction(any());
    }

    @Test
    void getStatistics_NoTransactions_ReturnsZeroStatistics() {
        when(repository.getAllTransactions()).thenReturn(List.of());

        Statistic stats = service.getStatistics();

        assertAll(
                () -> assertEquals(0, stats.getCount()),
                () -> assertEquals(0.0, stats.getSum()),
                () -> assertEquals(0.0, stats.getAverage()),
                () -> assertEquals(0.0, stats.getMin()),
                () -> assertEquals(0.0, stats.getMax())
        );
    }

    @Test
    void getStatistics_RecentTransactions_CalculatesCorrectStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction t1 = new Transaction(150.0, now.minusSeconds(30));
        Transaction t2 = new Transaction(250.0, now.minusSeconds(45));
        when(repository.getAllTransactions()).thenReturn(List.of(t1, t2));

        Statistic stats = service.getStatistics();

        assertAll(
                () -> assertEquals(2, stats.getCount()),
                () -> assertEquals(400.0, stats.getSum()),
                () -> assertEquals(200.0, stats.getAverage()),
                () -> assertEquals(150.0, stats.getMin()),
                () -> assertEquals(250.0, stats.getMax())
        );
    }

    @Test
    void getStatistics_OldTransactions_ExcludesFromStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction recent = new Transaction(100.0, now.minusSeconds(59));
        Transaction old = new Transaction(200.0, now.minusSeconds(61));
        when(repository.getAllTransactions()).thenReturn(List.of(recent, old));

        Statistic stats = service.getStatistics();

        assertAll(
                () -> assertEquals(1, stats.getCount()),
                () -> assertEquals(100.0, stats.getSum()),
                () -> assertEquals(100.0, stats.getAverage()),
                () -> assertEquals(100.0, stats.getMin()),
                () -> assertEquals(100.0, stats.getMax())
        );
    }

    @Test
    void getStatistics_TransactionExactly60Seconds_ExcludesFromStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction exact = new Transaction(100.0, now.minusSeconds(60));
        when(repository.getAllTransactions()).thenReturn(List.of(exact));

        Statistic stats = service.getStatistics();

        assertAll(
                () -> assertEquals(0, stats.getCount()),
                () -> assertEquals(0.0, stats.getSum()),
                () -> assertEquals(0.0, stats.getAverage()),
                () -> assertEquals(0.0, stats.getMin()),
                () -> assertEquals(0.0, stats.getMax())
        );
    }

    @Test
    void deleteAllTransactions_CallsRepositoryDelete() {
        service.deleteAllTransactions();
        verify(repository).deleteAllTransactions();
    }
}