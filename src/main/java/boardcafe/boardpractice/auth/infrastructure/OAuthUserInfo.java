package boardcafe.boardpractice.auth.infrastructure;

import boardcafe.boardpractice.member.domain.Member;

public interface OAuthUserInfo {

    Long getUserId();
    String getNickname();
    String getImageUrl();
    String getName();
    String getEmail();
    Member toMember();
}
