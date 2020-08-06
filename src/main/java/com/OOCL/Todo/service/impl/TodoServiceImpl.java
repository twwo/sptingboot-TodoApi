package com.OOCL.Todo.service.impl;

import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public List<Todo> findAll() {
        return null;
    }
}
