package itau.desafio_api_itau.services;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.service_contracts.ITransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
    @Override
    public void addTransaction(TransactionRequestDTO transactionRequest) {

    }

    @Override
    public void getStatistics() {

    }

    @Override
    public void deleteAllTransactions() {

    }
}
