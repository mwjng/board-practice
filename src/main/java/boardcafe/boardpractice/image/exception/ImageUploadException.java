package boardcafe.boardpractice.image.exception;

import boardcafe.boardpractice.common.exception.BadRequestException;
import boardcafe.boardpractice.common.exception.ErrorCode;

public class ImageUploadException extends BadRequestException {

    public ImageUploadException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
