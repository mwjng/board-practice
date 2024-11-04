package boardcafe.boardpractice.article.presentation;

import boardcafe.boardpractice.article.application.ArticleService;
import boardcafe.boardpractice.article.application.response.ArticlesCursorResponse;
import boardcafe.boardpractice.article.application.response.ArticlesOffsetResponse;
import boardcafe.boardpractice.article.presentation.request.ArticlesCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/paging/offset")
    public ResponseEntity<ArticlesOffsetResponse> getArticlesByOffset(
        @PageableDefault(size = 10) final Pageable pageable
    ) {
        return ResponseEntity.ok().body(articleService.getAllArticlesWithOffset(pageable));
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity<ArticlesCursorResponse> getArticlesByCursor(
        @RequestParam(required = false, defaultValue = "10") int size,
        @RequestParam(required = false, defaultValue = "0") Long cursorId
    ) {
        return ResponseEntity.ok().body(articleService.getAllArticlesWithCursor(size, cursorId));
    }

    @ResponseStatus(CREATED)
    @PostMapping("/make")
    public void createArticles(
        @Valid @RequestBody final ArticlesCreateRequest request
    ) {
        articleService.saveAll(request.convertToServiceRequestList());
    }
}
