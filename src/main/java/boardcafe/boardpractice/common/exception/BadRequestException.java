package boardcafe.boardpractice.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
