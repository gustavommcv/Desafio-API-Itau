package itau.desafio_api_itau.DTOS;

import java.time.OffsetDateTime;

public class TransactionRequestDTO {
    private Double value;
    private OffsetDateTime date;

    public TransactionRequestDTO(Double value, OffsetDateTime date) {
        this.value = value;
        this.date = date;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
