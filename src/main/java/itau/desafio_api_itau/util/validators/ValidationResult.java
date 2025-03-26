package itau.desafio_api_itau.util.validators;

import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private final List<String> errors;
    private final HttpStatus status;

    public ValidationResult(List<String> errors, HttpStatus status) {
        this.errors = errors;
        this.status = status;
    }

    public static ValidationResult valid() {
        return new ValidationResult(Collections.emptyList(), HttpStatus.CREATED);
    }

    public static ValidationResult invalid(List<String> errors, HttpStatus status) {
        return new ValidationResult(errors, status);
    }

    public boolean isValid() {
        return status == HttpStatus.CREATED;
    }

    public List<String> getErrors() {
        return errors;
    }

    public HttpStatus getStatus() {
        return status;
    }
}