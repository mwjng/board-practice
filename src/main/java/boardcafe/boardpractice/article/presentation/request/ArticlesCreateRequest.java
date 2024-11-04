package boardcafe.boardpractice.article.presentation.request;

import boardcafe.boardpractice.article.application.request.ArticleCreateServiceRequest;
import jakarta.validation.Valid;

import java.util.List;

public record ArticlesCreateRequest (
    @Valid List<ArticleCreateRequest> articles
){
    public List<ArticleCreateServiceRequest> convertToServiceRequestList() {
        return articles.stream()
            .map(ArticleCreateRequest::toServiceRequest)
            .toList();
    }
}
