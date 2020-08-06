package com.OOCL.Todo.integration;

import com.OOCL.Todo.model.Todo;
import com.OOCL.Todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    private final Todo testTodo = new Todo(null, "todo1", false);

    @AfterEach
    private void afterEach() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todoList_when_hit_getTodoList_given_none() throws Exception {
        //given
        todoRepository.save(testTodo);

        //when
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value(testTodo.getContent()))
                .andExpect(jsonPath("$[0].status").value(testTodo.getStatus()));
    }

    @Test
    void should_create_todo_when_hit_add_todo_given_todo_info() throws Exception {
        //given
        String addTodoInfo = "{\n" +
                "    \"content\" : \"test3\",\n" +
                "    \"status\" : false\n" +
                "}";

        //when
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(addTodoInfo))
                .andExpect(status().isCreated());
        Todo addedTodo = todoRepository.findAll().get(0);
        assertEquals("test3", addedTodo.getContent());
        assertFalse(addedTodo.getStatus());
    }

    @Test
    void should_update_todo_when_hit_update_todo_given_todo_info_and_id() throws Exception {
        //given
        Todo addedTodo = todoRepository.save(testTodo);
        System.out.println(addedTodo);
        String updateTodoInfo = "{\n" +
                "    \"id\": " + addedTodo.getId() + ",\n" +
                "    \"content\" : \"test1\",\n" +
                "    \"status\" : true\n" +
                "}";

        //when
        mockMvc.perform(put("/todos/" + addedTodo.getId()).contentType(MediaType.APPLICATION_JSON).content(updateTodoInfo))
                .andExpect(status().isOk());

        Todo updatedTodo = todoRepository.findById(addedTodo.getId()).orElse(null);
        assertNotNull(updatedTodo);
        assertEquals(addedTodo.getId(), updatedTodo.getId());
        assertEquals("test1", updatedTodo.getContent());
        assertTrue(updatedTodo.getStatus());
    }

    @Test
    void should_delete_todo_when_hit_delete_todo_given_id() throws Exception {
        //given
        Todo addedTodo = todoRepository.save(testTodo);

        //when
        mockMvc.perform(delete("/todos/" + addedTodo.getId()))
                .andExpect(status().isOk());
        Todo deletedTodo = todoRepository.findById(addedTodo.getId()).orElse(null);
        assertNull(deletedTodo);
    }
}
