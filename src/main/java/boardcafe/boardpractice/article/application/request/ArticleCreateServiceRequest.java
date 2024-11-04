package boardcafe.boardpractice.article.application.request;

import java.time.LocalDateTime;

public record ArticleCreateServiceRequest(
    Long articleNo,
    String title,
    LocalDateTime createdAt
) {
}
