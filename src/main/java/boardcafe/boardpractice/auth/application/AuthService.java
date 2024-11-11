package boardcafe.boardpractice.auth.application;

import boardcafe.boardpractice.auth.exception.InvalidTokenException;
import boardcafe.boardpractice.auth.infrastructure.OAuthProvider;
import boardcafe.boardpractice.auth.infrastructure.OAuthUserInfo;
import boardcafe.boardpractice.auth.domain.RefreshToken;
import boardcafe.boardpractice.auth.domain.repository.RefreshTokenRepository;
import boardcafe.boardpractice.auth.infrastructure.JwtProvider;
import boardcafe.boardpractice.member.domain.Member;
import boardcafe.boardpractice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static boardcafe.boardpractice.common.exception.ErrorCode.INVALID_REFRESH_TOKEN;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final OAuthProvider provider;

    @Transactional
    public AuthToken login(final String code, LocalDateTime time) {
        final OAuthUserInfo userInfo = provider.getOAuthUserInfo(code);
        final Member member = findOrCreateMember(userInfo);
        member.updateLoginDate(time);
        final AuthToken authToken = jwtProvider.generateAccessAndRefreshToken(member.getId().toString());
        saveRefreshToken(authToken, member);
        return authToken;
    }

    public AccessTokenResponse renewalAccessToken(final String refreshToken) {
        jwtProvider.validateRefreshToken(refreshToken);
        final String memberId = refreshTokenRepository.findById(refreshToken)
            .orElseThrow(() -> new InvalidTokenException(INVALID_REFRESH_TOKEN))
            .getMemberId().toString();
        return new AccessTokenResponse(jwtProvider.generateAccessToken(memberId));
    }

    private Member findOrCreateMember(final OAuthUserInfo userInfo) {
        return memberRepository.findByUserId(userInfo.getUserId())
            .orElseGet(() -> memberRepository.save(userInfo.toMember()));
    }

    private void saveRefreshToken(final AuthToken authToken, final Member member) {
        final RefreshToken refreshToken = new RefreshToken(authToken.refreshToken(), member.getId());
        refreshTokenRepository.save(refreshToken);
    }

    public void removeRefreshToken(final String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}