package todo.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public class TodoBuilder {
    private static TodoBuilder builder = new TodoBuilder();
    private String id = null;
    private String title = "";
    private String details = "";
    private String priority = "";
    private String status = "";
    private String author = "";
    private String executor = "";

    private TodoBuilder() {
    }

    private static TodoBuilder create() {
        return builder;
    }

    public TodoBuilder withFields(
            String title,
            String details,
            String priority,
            String status,
            String author,
            String executor
    ) {
        this.title = title;
        this.details = details;
        this.priority = priority;
        this.status = status;
        this.author = author;
        this.executor = executor;
        return builder;
    }

    public TodoBuilder withId(String id) {
        this.id = UUID.randomUUID().toString();
        return builder;
    }

    public Todo build() {
        Todo result = new Todo(
                this.title,
                this.details,
                this.priority,
                this.status,
                this.author,
                this.executor);
        if (id != null)
            result.setId(id);
        return result;
    }
}
