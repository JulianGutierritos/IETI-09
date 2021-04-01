package eci.ieti.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import eci.ieti.data.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    Page<Todo> findByResponsible (String responsible, Pageable pageable);

    List<Todo> findByResponsible (String responsible);

    List<Todo> findByDueDateBefore (Date date);

    List<Todo> findByPriorityGreaterThanAndResponsible (int priority, String responsible);

    List<Todo> findByDescriptionMatchesRegex (String regex);
    
}
