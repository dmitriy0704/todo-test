package todo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    public record TodosV1Presentation(
            String id, String title, String description) {
    }

    public record NewProductPayloadV1(
            String title, String description) {
    }


    @GetMapping
    public ResponseEntity<List<TodosV1Presentation>> getProducts() {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/todos+json"))
                .body(List.of(
                        new TodosV1Presentation("1",
                                "Купить хлеб",
                                "Купить хлеб 1 булку"),
                        new TodosV1Presentation("2",
                                "Купить молоко",
                                "Купить молоко 2л")
                ));
    }


    @PostMapping
    public ResponseEntity<TodosV1Presentation> createProduct(
            @RequestBody NewProductPayloadV1 payload, UriComponentsBuilder uriComponentsBuilder) {
        if (payload.title == null) {
            return ResponseEntity.badRequest().build();
        }

        var id = UUID.randomUUID();
        return ResponseEntity.created(uriComponentsBuilder.pathSegment(id.toString())
                        .build(Map.of()))
                .contentType(MediaType.valueOf("application/todos+json"))
                .body(new TodosV1Presentation(id.toString(), payload.title, payload.description));
    }


}
