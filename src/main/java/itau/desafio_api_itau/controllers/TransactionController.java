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
    public ResponseEntity<Object> postTransacao() {
//        Request
//        Tenham os campos valor e dataHora preenchidos
//        A transação NÃO DEVE acontecer no futuro
//        A transação DEVE ter acontecido a qualquer momento no passado
//        A transação NÃO DEVE ter valor negativo
//        A transação DEVE ter valor igual ou maior que 0 (zero)

//        Response
//        201 Created sem nenhum corpo
//        A transação foi aceita (ou seja foi validada, está válida e foi registrada)
//        422 Unprocessable Entity sem nenhum corpo
//        A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0)
//        400 Bad Request sem nenhum corpo
//        A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)

        return ResponseEntity.ok("post Transacao");
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Object> deleteTransacao() {
//        Response
//        200 OK sem nenhum corpo
//        Todas as informações foram apagadas com sucesso

        return ResponseEntity.ok("delete Transacao");
    }

    @GetMapping("/estatistica")
    public ResponseEntity<Object> getEstatistica() {
//        Response
//        200 OK com os dados das estatísticas
//        Um JSON com os campos count, sum, avg, min e max todos preenchidos com seus respectivos valores
//        Atenção! Quando não houverem transações nos últimos 60 segundos considere todos os valores como 0 (zero

        return ResponseEntity.ok("get Estatistica");
    }
}
