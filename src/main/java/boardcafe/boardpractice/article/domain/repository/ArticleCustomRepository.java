package boardcafe.boardpractice.article.domain.repository;

import boardcafe.boardpractice.article.domain.Article;

import java.util.List;

public interface ArticleCustomRepository {

    void saveAllInBatch(List<Article> articles);
}
