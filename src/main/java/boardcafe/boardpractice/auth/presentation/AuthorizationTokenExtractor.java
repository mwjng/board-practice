package boardcafe.boardpractice.auth.presentation;

import boardcafe.boardpractice.common.exception.BadRequestException;
import org.springframework.stereotype.Component;

import static boardcafe.boardpractice.common.exception.ErrorCode.INVALID_REQUEST;

@Component
public class AuthorizationTokenExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public String extract(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER_TYPE)) {
            return authorizationHeader.substring(BEARER_TYPE.length()).trim();
        }
        throw new BadRequestException(INVALID_REQUEST);
    }
}
