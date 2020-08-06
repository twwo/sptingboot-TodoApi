package com.OOCL.Todo.service;

import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.repository.TodoRepository;
import com.OOCL.Todo.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TodoServiceTest {

    private final List<Todo> testTodoList = Arrays.asList(
            new Todo(1, "todo1", false),
            new Todo(2, "todo2", false),
            new Todo(3, "todo3", false));

    private TodoRepository todoRepository;
    private TodoService todoService;

    @BeforeEach
    private void beforeEach() {
        todoRepository = mock(TodoRepository.class);
        todoService = new TodoServiceImpl(todoRepository);
    }

    @Test
    void should_return_todo_list_when_getTodoList_given_todoList() {
        //given
        given(todoRepository.findAll()).willReturn(testTodoList);

        //when
        List<Todo> todoList = todoService.findAll();

        //then
        assertEquals(3, todoList.size());
    }

    @Test
    void should_return_todo_when_findById_given_id() {
        //given
        Todo addedTodo = testTodoList.get(0);
        given(todoRepository.findById(addedTodo.getId())).willReturn(Optional.of(addedTodo));

        //when
        Todo todo = todoService.findById(addedTodo.getId());

        //then
        assertNotNull(todo);
        assertEquals(addedTodo.getId(), todo.getId());
        assertEquals(addedTodo.getContent(), todo.getContent());
        assertEquals(addedTodo.getStatus(), todo.getStatus());
    }

    @Test
    void should_add_todo_when_save_given_todo() {
        //given
        Todo addedTodo = testTodoList.get(0);
        given(todoRepository.save(addedTodo)).willReturn(addedTodo);

        //when
        Todo todo = todoService.add(addedTodo);

        //then
        assertEquals(addedTodo, todo);
    }

    @Test
    void should_update_todo_when_update_given_todo() {
        //given
        Todo updatedTodo = testTodoList.get(0);
        given(todoRepository.save(updatedTodo)).willReturn(updatedTodo);
        given(todoRepository.findById(updatedTodo.getId())).willReturn(Optional.of(updatedTodo));

        //when
        Todo todo = todoService.update(updatedTodo.getId(), updatedTodo);

        //then
        assertNotNull(todo);
        assertEquals(updatedTodo, todo);
    }


}
