package boardcafe.boardpractice.member.exception;

import boardcafe.boardpractice.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserNotFoundException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
