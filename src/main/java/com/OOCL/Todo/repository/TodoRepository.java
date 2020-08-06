package com.OOCL.Todo.repository;

import com.OOCL.Todo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    List<Todo> findAll();
}
