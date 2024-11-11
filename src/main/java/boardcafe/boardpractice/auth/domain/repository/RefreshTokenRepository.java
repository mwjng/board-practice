package boardcafe.boardpractice.auth.domain.repository;

import boardcafe.boardpractice.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
