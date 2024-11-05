package boardcafe.boardpractice.article.application;

import boardcafe.boardpractice.article.application.request.ArticleCreateServiceRequest;
import boardcafe.boardpractice.article.application.response.ArticleResponse;
import boardcafe.boardpractice.article.application.response.ArticlesCursorResponse;
import boardcafe.boardpractice.article.application.response.ArticlesOffsetResponse;
import boardcafe.boardpractice.article.domain.Article;
import boardcafe.boardpractice.article.domain.repository.ArticleRepository;
import boardcafe.boardpractice.article.infrastructure.ArticleJdbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private static final String DEFAULT_SORT_FIELD = "articleNo";

    private final ArticleRepository articleRepository;
    private final ArticleJdbcRepository articleJdbcRepository;

    public ArticlesOffsetResponse getAllArticlesWithOffset(final Pageable pageable) {
        final Page<Article> articles = articleRepository.findAll(pageable.previousOrFirst());
        return getArticlesOffsetResponse(articles);
    }

    public ArticlesCursorResponse getAllArticlesWithCursor(final int size, final Long cursorId) {
        final PageRequest pageRequest = PageRequest.of(0, size, Sort.by(DEFAULT_SORT_FIELD).ascending());
        final Slice<Article> articlesAfterCursorId = articleRepository.findArticlesAfterCursorId(cursorId, pageRequest);
        return getArticlesCursorResponse(articlesAfterCursorId);
    }

    @Transactional
    public void saveAll(final List<ArticleCreateServiceRequest> requests) {
        final List<Article> articles = requests.stream()
            .map(request -> new Article(request.articleNo(), request.title()))
            .toList();
        articleJdbcRepository.saveAll(articles);
    }

    private ArticlesOffsetResponse getArticlesOffsetResponse(final Page<Article> page) {
        final List<Article> articles = page.getContent();
        return new ArticlesOffsetResponse(
            page.getTotalPages(),
            getArticleResponses(articles)
        );
    }

    private ArticlesCursorResponse getArticlesCursorResponse(final Slice<Article> slice) {
        final List<Article> articles = slice.getContent();
        return new ArticlesCursorResponse(
            getLastId(slice),
            getArticleResponses(articles)
        );
    }

    private List<ArticleResponse> getArticleResponses(final List<Article> articles) {
        return articles.stream()
            .map(ArticleResponse::of)
            .toList();
    }

    private Long getLastId(final Slice<Article> slice) {
        return slice.hasContent() ? slice.getContent().getLast().getId() : null;
    }
}
