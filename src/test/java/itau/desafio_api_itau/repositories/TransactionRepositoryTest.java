package itau.desafio_api_itau.repositories;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Test
    void testAddTransaction_SavesToInMemoryList() {
        // Arrange
        TransactionRequestDTO request = new TransactionRequestDTO(
                100.0,
                OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00")
        );

        // Act
        transactionRepository.addTransaction(request);

        // Assert
        Transaction savedTransaction = transactionRepository.getAllTransactions().getFirst();

        assertEquals(1, transactionRepository.getAllTransactions().size());
        assertEquals(100.0, savedTransaction.getValue());
        assertEquals(
                OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"),
                savedTransaction.getDate()
        );
    }

    @Test
    void testDeleteAllTransactions_ClearsList() {
        // Arrange
        transactionRepository.addTransaction(new TransactionRequestDTO(50.0, OffsetDateTime.now()));
        transactionRepository.addTransaction(new TransactionRequestDTO(150.0, OffsetDateTime.now()));

        // Act
        boolean result = transactionRepository.deleteAllTransactions();

        // Assert
        assertTrue(result);
        assertEquals(0, transactionRepository.getAllTransactions().size());
    }
}
