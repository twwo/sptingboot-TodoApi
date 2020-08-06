package com.OOCL.Todo.service;

import com.OOCL.Todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();


    Todo findById(Integer id);

    Todo add(Todo addedTodo);
}
