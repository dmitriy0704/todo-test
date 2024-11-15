package todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
public record TodoR(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id,
        String title,
        String description,
        Timestamp created,
        Timestamp modified,
        String priority,
        String status,
        boolean completed,
        String author,
        String executor
) {}
