package itau.desafio_api_itau.DTOS;

import java.time.OffsetDateTime;

public class TransactionRequestDTO {
    public Double value;
    public OffsetDateTime date;

    public TransactionRequestDTO(Double value, OffsetDateTime date) {
        this.value = value;
        this.date = date;
    }
}
