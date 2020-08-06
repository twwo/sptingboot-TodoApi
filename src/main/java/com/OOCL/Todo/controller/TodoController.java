package com.OOCL.Todo.controller;

import com.OOCL.Todo.exception.GlobalException;
import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.findAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.add(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todo) throws GlobalException {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable Integer id) throws GlobalException {
        return todoService.delete(id);
    }
}
