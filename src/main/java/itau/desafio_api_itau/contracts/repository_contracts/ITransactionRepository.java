package itau.desafio_api_itau.contracts.repository_contracts;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.models.Transaction;

import java.util.List;

public interface ITransactionRepository {
    // Returns all the transactions stored in the Database
    List<Transaction> getAllTransactions();

    // Add a transaction to the data base with a transaction request dto object
    void addTransaction(TransactionRequestDTO transactionRequest);

    // Delete all transactions
    int deleteAllTransactions();
}
