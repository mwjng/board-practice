package boardcafe.boardpractice.auth.application;

public record AuthToken(
    String accessToken,
    String refreshToken
) {
}
