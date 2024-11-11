package boardcafe.boardpractice.auth.infrastructure;

public interface OAuthProvider {

    OAuthUserInfo getOAuthUserInfo(String code);
}
