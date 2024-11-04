package boardcafe.boardpractice.article.application.response;

import boardcafe.boardpractice.article.domain.Article;

import java.time.LocalDateTime;

public record ArticleResponse(
    Long id,
    String title,
    LocalDateTime createdAt
) {
    public static ArticleResponse of(final Article article) {
        return new ArticleResponse(
            article.getArticleNo(),
            article.getTitle(),
            article.getCreatedAt()
        );
    }
}
