package itau.desafio_api_itau.repositories;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.data.DbContext;
import itau.desafio_api_itau.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository implements ITransactionRepository {

    private final DbContext _dbContext = new DbContext();

    @Override
    public List<Transaction> getAllTransactions() {
        return _dbContext.findAll();
    }

    @Override
    public void addTransaction(TransactionRequestDTO transactionRequest) {
        _dbContext.save(transactionRequest);
    }

    @Override
    public int deleteAllTransactions() {
        return _dbContext.dropAll();
    }
}
