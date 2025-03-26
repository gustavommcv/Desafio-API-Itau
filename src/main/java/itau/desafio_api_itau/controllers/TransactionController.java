package itau.desafio_api_itau.controllers;

import itau.desafio_api_itau.DTOS.TransactionRequestDTO;
import itau.desafio_api_itau.contracts.service_contracts.ITransactionService;
import itau.desafio_api_itau.models.Statistic;
import itau.desafio_api_itau.util.validators.ValidationResult;
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
    private final ITransactionService service;

    public TransactionController(ITransactionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        // Building the response JSON for all the endpoints
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

    @PostMapping("/transacao")
    public ResponseEntity<Void> postTransacao(@RequestBody TransactionRequestDTO requestDTO) {
        ValidationResult result = service.addTransaction(requestDTO);
        return ResponseEntity.status(result.getStatus()).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao() {
//        Response
//        200 OK sem nenhum corpo
//        Todas as informações foram apagadas com sucesso
        service.deleteAllTransactions();
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<Object> getEstatistica() {
//        Response
//        200 OK com os dados das estatísticas
//        Um JSON com os campos count, sum, avg, min e max todos preenchidos com seus respectivos valores
//        Atenção! Quando não houverem transações nos últimos 60 segundos considere todos os valores como 0 (zero

        return ResponseEntity.ok(service.getStatistics());
    }
}
