package boardcafe.boardpractice.auth.presentation;

import boardcafe.boardpractice.auth.application.AccessTokenResponse;
import boardcafe.boardpractice.auth.application.AuthService;
import boardcafe.boardpractice.auth.application.AuthToken;
import boardcafe.boardpractice.member.application.MemberService;
import boardcafe.boardpractice.auth.presentation.request.LoginMember;
import boardcafe.boardpractice.auth.presentation.request.TokenRequest;
import boardcafe.boardpractice.member.application.response.MemberNicknameResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/auth")
    public ResponseEntity<AccessTokenResponse> generateAccessAndRefreshToken(
        @Valid @RequestBody final TokenRequest tokenRequest
    ) {
        final AuthToken authToken = authService.login(tokenRequest.code(), now());
        final ResponseCookie cookie = ResponseCookie.from("refreshToken", authToken.refreshToken())
            .maxAge(3600)
            .httpOnly(true)
            .sameSite("none")
            .secure(true)
            .build();

        return ResponseEntity.status(CREATED)
            .header(SET_COOKIE, cookie.toString())
            .body(new AccessTokenResponse(authToken.accessToken()));
    }

    @GetMapping("/member")
    public ResponseEntity<MemberNicknameResponse> getMemberNickname(
        @AuthenticationPrincipal final LoginMember loginMember
    ) {
        return ResponseEntity.status(OK)
            .body(memberService.getNicknameById(loginMember.memberId()));
    }

    @GetMapping("/reissue")
    public ResponseEntity<AccessTokenResponse> reissueAccessToken(
        @CookieValue("refreshToken") final String refreshToken
    ) {
        return ResponseEntity.status(CREATED)
            .body(authService.renewalAccessToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
        @AuthenticationPrincipal final LoginMember loginMember,
        @CookieValue("refreshToken") final String refreshToken
    ) {
        authService.removeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
