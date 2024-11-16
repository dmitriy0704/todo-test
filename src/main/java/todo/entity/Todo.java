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

    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;
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

    public Todo(String title,
                String description,
                String priority,
                String status,
                String author,
                String executor) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.author = author;
        this.executor = executor;
    }
}
