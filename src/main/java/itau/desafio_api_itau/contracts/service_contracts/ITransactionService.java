package itau.desafio_api_itau.contracts.service_contracts;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.util.validators.ValidationResult;

public interface ITransactionService {
    // Add a transaction to the data base with a transaction request dto object
    ValidationResult addTransaction(TransactionRequestDTO transactionRequest);

    // Get statistics on transactions that have taken place in the last 60 seconds
    void getStatistics();

    // Delete all transactions
    void deleteAllTransactions();
}
