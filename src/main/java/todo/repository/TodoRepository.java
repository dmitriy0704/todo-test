package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.entity.ToDo;

public interface TodoRepository extends CrudRepository<ToDo, String> {
}
