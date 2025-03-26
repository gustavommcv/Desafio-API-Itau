package itau.desafio_api_itau.models;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private Double value;
    private OffsetDateTime date;

    public Transaction(Double value, OffsetDateTime date) {
        this.value = value;
        this.date = date;

        this.id = UUID.randomUUID();
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
