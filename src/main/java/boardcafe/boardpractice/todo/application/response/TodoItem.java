package boardcafe.boardpractice.todo.application.response;

import boardcafe.boardpractice.todo.domain.Todo;

public record TodoItem(Long id, String content, boolean completed) {

    public static TodoItem of(Todo todo) {
        return new TodoItem(todo.getId(), todo.getContent(), todo.getCompleted());
    }
}
