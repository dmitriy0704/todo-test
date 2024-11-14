package todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import todo.entity.ToDo;
import todo.repository.TodoRepository;

@Controller
@RequestMapping("/")
public class ToDoController {

    private final TodoRepository todoRepository;


    public ToDoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping(value = "todos")
    public ResponseEntity<Iterable<ToDo>> getToDos(){

    }
}
