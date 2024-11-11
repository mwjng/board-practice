package boardcafe.boardpractice.auth.exception;

import boardcafe.boardpractice.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final ErrorCode errorCode;

    public AuthException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
