package boardcafe.boardpractice.article.application.response;

import java.util.List;

public record ArticlesCursorResponse(
    Long lastId,
    List<ArticleResponse> articles
) {
}
