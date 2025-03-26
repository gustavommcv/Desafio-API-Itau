package itau.desafio_api_itau.DTOS;

import java.time.OffsetDateTime;

public class TransactionResponseDTO {
    public Double value;
    public OffsetDateTime date;

    public TransactionResponseDTO(Double value, OffsetDateTime date) {
        this.value = value;
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }
}
