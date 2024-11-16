package todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Todo {


    //@GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String todoTitle;

    @NotBlank
    @Column(nullable = false)
    private String details;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotBlank
    @Column(nullable = false)
    private String priority;

    @NotBlank
    @Column(nullable = false)
    private String status;

    private boolean completed;

    @NotBlank
    @Column(nullable = false)
    private String author;

    @NotBlank
    @Column(nullable = false)
    private String executor;

    public Todo() {
        LocalDateTime now = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = now;
        this.modified = now;
    }

    public Todo(String todoTitle,
                String details,
                String priority,
                String status,
                String author,
                String executor) {
        this.todoTitle = todoTitle;
        this.details = details;
        this.priority = priority;
        this.status = status;
        this.author = author;
        this.executor = executor;
    }
}
