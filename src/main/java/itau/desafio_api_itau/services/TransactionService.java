package itau.desafio_api_itau.services;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.contracts.service_contracts.ITransactionService;
import itau.desafio_api_itau.models.Statistic;
import itau.desafio_api_itau.models.Transaction;
import itau.desafio_api_itau.util.validators.TransactionValidator;
import itau.desafio_api_itau.util.validators.ValidationResult;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValidationResult addTransaction(TransactionRequestDTO transactionRequest) {
        ValidationResult validationResult = TransactionValidator.validate(transactionRequest);

        if (validationResult.isValid()) {
            repository.addTransaction(transactionRequest);
        }

        return validationResult;
    }

    @Override
    public Statistic getStatistics() {
        List<Transaction> allTransactions = repository.getAllTransactions();
        OffsetDateTime sixtySecondsAgo = OffsetDateTime.now().minusSeconds(60);

        List<Transaction> recentTransactions = allTransactions.stream()
                .filter(t -> t.getDate().isAfter(sixtySecondsAgo))
                .toList();

        if (recentTransactions.isEmpty()) {
            return new Statistic(0, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics stats = recentTransactions.stream()
                .mapToDouble(Transaction::getValue)
                .summaryStatistics();

        return new Statistic(
                recentTransactions.size(),
                stats.getMax(),
                stats.getMin(),
                stats.getAverage(),
                stats.getSum()
        );
    }

    @Override
    public void deleteAllTransactions() {
//        Response
//        200 OK sem nenhum corpo
//        Todas as informações foram apagadas com sucesso

        repository.deleteAllTransactions();
    }
}
