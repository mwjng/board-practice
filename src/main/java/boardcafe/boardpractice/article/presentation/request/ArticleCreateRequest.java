package boardcafe.boardpractice.article.presentation.request;

import boardcafe.boardpractice.article.application.request.ArticleCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ArticleCreateRequest (
    @NotNull(message = "id 값이 null 입니다.")
    Long id,

    @NotBlank(message = "제목을 입력하세요.")
    String title,

    @NotNull(message = "생성 일자가 null 입니다.")
    LocalDateTime createdAt
){
    ArticleCreateServiceRequest toServiceRequest() {
        return new ArticleCreateServiceRequest(id, title, createdAt);
    }
}
