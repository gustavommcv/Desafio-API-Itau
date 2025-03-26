package itau.desafio_api_itau.util.validators;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import org.springframework.http.HttpStatus;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionValidator {

    public static ValidationResult validate(TransactionRequestDTO transactionRequest) {
        List<String> errors = new ArrayList<>();

        // Validação de JSON inválido/corpo ausente (400 Bad Request)
        if (transactionRequest == null) {
            return new ValidationResult(
                    List.of("Corpo da requisição inválido ou ausente"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validação de campos obrigatórios (422 Unprocessable Entity)
        if (transactionRequest.getValue() == null) {
            errors.add("O campo 'valor' é obrigatório");
        }
        if (transactionRequest.getDate() == null) {
            errors.add("O campo 'dataHora' é obrigatório");
        }

        // Se campos obrigatórios estiverem ausentes, retorne imediatamente
        if (!errors.isEmpty()) {
            return ValidationResult.invalid(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        // Validação de regras de negócio (422 Unprocessable Entity)
        if (transactionRequest.getValue() < 0) {
            errors.add("O 'valor' não pode ser negativo");
        }

        OffsetDateTime currentDateTime = OffsetDateTime.now();
        if (transactionRequest.getDate().isAfter(currentDateTime)) {
            errors.add("A 'dataHora' não pode ser futura");
        }

        if (!errors.isEmpty()) {
            return ValidationResult.invalid(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ValidationResult.valid();
    }
}