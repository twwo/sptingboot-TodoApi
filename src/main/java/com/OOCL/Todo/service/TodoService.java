package com.OOCL.Todo.service;

import com.OOCL.Todo.exception.GlobalException;
import com.OOCL.Todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo findById(Integer id) throws GlobalException;

    Todo add(Todo addedTodo);

    Todo update(Integer id, Todo updatedTodo) throws GlobalException;

    Todo delete(Integer id) throws GlobalException;
}
