package itau.desafio_api_itau.repositories;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.models.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private ITransactionRepository transactionRepository;

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAllTransactions();
    }

    @Test
    @DisplayName("should return empty list when there are no transactions")
    void getAllTransactions_WhenEmpty_ShouldReturnEmptyList() {
        // Act
        List<Transaction> transactions = transactionRepository.getAllTransactions();

        // Assert
        assertTrue(transactions.isEmpty(), "A lista de transações deve estar vazia");
    }

    @Test
    @DisplayName("should persist a transaction when adding it")
    void addTransaction_ValidRequest_ShouldPersistTransaction() {
        // Arrange
        TransactionRequestDTO request = new TransactionRequestDTO(
                100.0,
                OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00")
        );

        // Act
        transactionRepository.addTransaction(request);

        // Assert
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        assertFalse(transactions.isEmpty(), "Nenhuma transação foi persistida");

        Transaction savedTransaction = transactions.getFirst();
        assertAll("Verificação dos campos da transação",
                () -> assertEquals(100.0, savedTransaction.getValue(), "Valor incorreto"),
                () -> assertEquals(
                        OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"),
                        savedTransaction.getDate(),
                        "Data incorreta"
                )
        );
    }

    @Test
    @DisplayName("should remove all transactions and return correct count when deleting")
    void deleteAllTransactions_ExistingTransactions_ShouldClearRepository() {
        // Arrange
        transactionRepository.addTransaction(new TransactionRequestDTO(50.0, OffsetDateTime.now()));
        transactionRepository.addTransaction(new TransactionRequestDTO(150.0, OffsetDateTime.now()));
        int initialSize = transactionRepository.getAllTransactions().size();

        // Act
        int deletedCount = transactionRepository.deleteAllTransactions();

        // Assert
        assertEquals(initialSize, deletedCount, "Deveria retornar o número correto de transações deletadas");
        assertEquals(0, transactionRepository.getAllTransactions().size(), "Repositório deve estar vazio");
    }

    @Test
    @DisplayName("should return 0 when no transactions to delete")
    void deleteAllTransactions_WhenEmpty_ShouldReturnZero() {
        // Act
        int deletedCount = transactionRepository.deleteAllTransactions();

        // Assert
        assertEquals(0, deletedCount, "Deveria retornar 0 quando não há transações");
    }
}