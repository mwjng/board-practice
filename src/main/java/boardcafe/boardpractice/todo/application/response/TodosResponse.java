package boardcafe.boardpractice.todo.application.response;

import java.util.List;

public record TodosResponse(List<TodoItem> todos) {
}