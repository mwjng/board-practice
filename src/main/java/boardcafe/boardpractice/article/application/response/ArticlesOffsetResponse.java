package boardcafe.boardpractice.article.application.response;

import java.util.List;

public record ArticlesOffsetResponse(
    int totalPage,
    List<ArticleResponse> articles
) {
}
