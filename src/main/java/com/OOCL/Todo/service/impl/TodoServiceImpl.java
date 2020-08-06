package com.OOCL.Todo.service.impl;

import com.OOCL.Todo.constant.ExceptionConstant;
import com.OOCL.Todo.exception.GlobalException;
import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.repository.TodoRepository;
import com.OOCL.Todo.service.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Integer id) throws GlobalException {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            throw new GlobalException(ExceptionConstant.NOT_SUCH_DATA.getMessage());
        }
        return todo;
    }

    @Override
    public Todo add(Todo addedTodo) {
        return todoRepository.save(addedTodo);
    }

    @Override
    public Todo update(Integer id, Todo updatedTodo) throws GlobalException {
        Todo todo = findById(id);
        if (todo != null) {
            BeanUtils.copyProperties(updatedTodo, todo);
            todoRepository.save(todo);
        }
        return todo;
    }

    @Override
    public Todo delete(Integer id) throws GlobalException {
        Todo todo = findById(id);
        if (todo != null) {
            todoRepository.deleteById(id);
        }
        return todo;
    }
}
