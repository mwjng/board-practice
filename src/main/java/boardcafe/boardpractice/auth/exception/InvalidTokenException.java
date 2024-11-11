package boardcafe.boardpractice.auth.exception;

import boardcafe.boardpractice.common.exception.ErrorCode;

public class InvalidTokenException extends AuthException {

    public InvalidTokenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
