package itau.desafio_api_itau.services;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.repository_contracts.ITransactionRepository;
import itau.desafio_api_itau.contracts.service_contracts.ITransactionService;
import itau.desafio_api_itau.util.validators.TransactionValidator;
import itau.desafio_api_itau.util.validators.ValidationResult;
import org.springframework.stereotype.Service;

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
    public void getStatistics() {
        //        Response
//        200 OK com os dados das estatísticas
//        Um JSON com os campos count, sum, avg, min e max todos preenchidos com seus respectivos valores
//        Atenção! Quando não houverem transações nos últimos 60 segundos considere todos os valores como 0 (zero
    }

    @Override
    public void deleteAllTransactions() {
//        Response
//        200 OK sem nenhum corpo
//        Todas as informações foram apagadas com sucesso
    }
}
