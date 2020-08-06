package com.OOCL.Todo.repository;

import com.OOCL.Todo.model.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
}
