package boardcafe.boardpractice.article.infrastructure;

import boardcafe.boardpractice.article.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<Article> articles) {
        String sql = "insert into article (article_no, title, created_at, modified_at) values(?,?, now(), now())";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                Article article = articles.get(i);
                ps.setLong(1, article.getArticleNo());
                ps.setString(2, article.getTitle());
            }

            @Override
            public int getBatchSize() {
                return articles.size();
            }
        });
    }
}
