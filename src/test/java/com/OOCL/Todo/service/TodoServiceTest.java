package com.OOCL.Todo.service;

import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.repository.TodoRepository;
import com.OOCL.Todo.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TodoServiceTest {

    private final List<Todo> testTodoList = Arrays.asList(
            new Todo(1, "todo1", false),
            new Todo(2, "todo2", false),
            new Todo(3, "todo3", false));

    @Test
    void should_return_todo_list_when_getTodoList_given_todoList() {
        //given
        TodoRepository todoRepository = mock(TodoRepository.class);
        given(todoRepository.findAll()).willReturn(testTodoList);
        TodoService todoService = new TodoServiceImpl(todoRepository);

        //when
        List<Todo> todoList = todoService.findAll();

        //then
        assertEquals(3, todoList.size());
    }
}
