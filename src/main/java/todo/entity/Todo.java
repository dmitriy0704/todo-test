package todo.entity;

import lombok.Data;

@Data
public class Todo {
    private String id;
    private String title;
    private String description;

    public Todo(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
