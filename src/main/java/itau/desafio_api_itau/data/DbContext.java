package itau.desafio_api_itau.data;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DbContext {
    private final List<Transaction> transactions;

    public DbContext() {
        transactions = new ArrayList<Transaction>();
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public void save(TransactionRequestDTO transactionRequest) {
        this.transactions.add(new Transaction(transactionRequest.getValue(), transactionRequest.getDate()));
    }

    public int dropAll() {
        var size = transactions.size();
        transactions.clear();
        return size;
    }
}
