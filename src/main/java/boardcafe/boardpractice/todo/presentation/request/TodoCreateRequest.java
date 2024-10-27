package boardcafe.boardpractice.todo.presentation.request;

import boardcafe.boardpractice.todo.application.request.TodoCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record TodoCreateRequest(
        @NotBlank(message = "해야 할 일을 필수로 입력해야 합니다.")
        String content
) {
    public TodoCreateServiceRequest toServiceRequest() {
        return new TodoCreateServiceRequest(content);
    }
}
