package boardcafe.boardpractice.auth.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuthAccessTokenResponse(
    @JsonProperty("access_token")
    String accessToken
) {
}
