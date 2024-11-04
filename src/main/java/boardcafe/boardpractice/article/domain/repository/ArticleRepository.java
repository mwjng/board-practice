package boardcafe.boardpractice.article.domain.repository;

import boardcafe.boardpractice.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> findAllBy(final Pageable pageable);

    @Query("select a from Article a where a.articleNo > :cursorId")
    Slice<Article> findArticlesAfterCursorId(final Long cursorId, final Pageable pageable);
}
