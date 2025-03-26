package itau.desafio_api_itau.data;

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
}
