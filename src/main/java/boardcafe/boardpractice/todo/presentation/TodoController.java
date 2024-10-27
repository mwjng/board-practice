package boardcafe.boardpractice.todo.presentation;

import boardcafe.boardpractice.todo.application.TodoService;
import boardcafe.boardpractice.todo.application.response.TodoItem;
import boardcafe.boardpractice.todo.application.response.TodosResponse;
import boardcafe.boardpractice.todo.presentation.request.TodoCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<TodosResponse> getTodos() {
        return ResponseEntity.ok().body(todoService.getAllTodos());
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoItem> createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest) {
        final TodoItem todoItem = todoService.save(todoCreateRequest.toServiceRequest());
        return ResponseEntity.created(URI.create("/todos/" + todoItem.id())).body(todoItem);
    }

    @PatchMapping("/todos/{todoId}")
    public ResponseEntity<Void> updateCompleted(@PathVariable Long todoId) {
        todoService.updateTodoCompleted(todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return ResponseEntity.noContent().build();
    }
}
