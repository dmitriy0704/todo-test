package todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private Timestamp created;

    private Timestamp modified;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)

    private String status;
    private boolean completed;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String executor;

    public Todo() {

    }
}
