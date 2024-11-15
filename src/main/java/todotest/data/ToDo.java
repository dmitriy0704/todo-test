package todotest.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String description;

    private Timestamp created;

    private Timestamp modified;

    private String priority;

    private String status;

    private boolean completed;

    public ToDo() {
    }
}
