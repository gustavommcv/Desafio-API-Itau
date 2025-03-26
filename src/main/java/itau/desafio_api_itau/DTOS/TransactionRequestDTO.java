package itau.desafio_api_itau.DTOS;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class TransactionRequestDTO {
    private Double value;
    private OffsetDateTime date;

    @JsonCreator
    public TransactionRequestDTO(
            @JsonProperty("valor") Double value,
            @JsonProperty("dataHora") OffsetDateTime date
    ) {
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
