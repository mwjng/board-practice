package boardcafe.boardpractice.auth.exception;

import boardcafe.boardpractice.common.exception.ErrorCode;

public class ExpiredTokenException extends AuthException {

    public ExpiredTokenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
