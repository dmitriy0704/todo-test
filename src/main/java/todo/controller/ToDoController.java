package todo.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.entity.Todo;
import todo.repository.TodoRepository;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private final TodoRepository todoRepository;


    public ToDoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @ArraySchema
    @GetMapping(value = "/todos")
    public ResponseEntity<Iterable<Todo>> getToDos(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<Iterable<Todo>>
                (this.todoRepository.findAll(),
                        headers, HttpStatus.OK);
    }
}
