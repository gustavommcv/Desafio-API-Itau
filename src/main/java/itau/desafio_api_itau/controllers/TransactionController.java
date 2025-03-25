package itau.desafio_api_itau.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Controller & ResponseBody(JSON/XML)
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE, // JSON Responses
        consumes = MediaType.APPLICATION_JSON_VALUE // JSON Requests
)
public class TransactionController {
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        List<Map<String, Object>> rotas = List.of(
                Map.of(
                        "path", "/estatistica",
                        "method", "GET",
                        "description", "Retorna estatísticas das transações",
                        "parameters", List.of()
                ),
                Map.of(
                        "path", "/transacao",
                        "method", "POST",
                        "description", "Este é o endpoint que irá receber as Transações. Cada transação consiste de um 'valor' e uma 'dataHora' de quando ela aconteceu.",
                        "parameters", List.of(Map.of(
                                        "name", "valor",
                                        "type", "Double",
                                        "description", "Valor monetário da transação",
                                        "required", true
                                ),
                                Map.of(
                                        "name", "dataHora",
                                        "type", "String",
                                        "format", "ISO 8601 (ex: 2024-01-25T15:30:00Z)",
                                        "required", true
                                ))
                ),
                Map.of(
                        "path", "/transacao",
                        "method", "DELETE",
                        "description", "Este endpoint simplesmente apaga todos os dados de transações que estejam armazenados.",
                        "parameters", List.of()
                )
        );

        return ResponseEntity.ok(Map.of(
                "endpoints", rotas
        ));
    }

    @GetMapping("/estatistica")
    public ResponseEntity<Object> getEstatistica() {

        return ResponseEntity.ok("post Transacao");
    }

    @PostMapping("/transacao")
    public ResponseEntity<Object> postTransacao() {
        return ResponseEntity.ok("post Transacao");
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Object> deleteTransacao() {
        return ResponseEntity.ok("delete Transacao");
    }
}
