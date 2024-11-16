package todo.repository;

import org.springframework.data.repository.CrudRepository;
import todo.entity.Todo;

import java.util.Collection;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, String> {
    Todo save(Todo domain);
    Iterable<Todo> save(Collection<Todo> domains);
    Optional<Todo> findById(String id);
    Iterable<Todo> findAll();
    void delete(Todo domain);
}
