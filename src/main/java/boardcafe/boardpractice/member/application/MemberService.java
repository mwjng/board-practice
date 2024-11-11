package boardcafe.boardpractice.member.application;

import boardcafe.boardpractice.member.application.response.MemberNicknameResponse;
import boardcafe.boardpractice.member.domain.repository.MemberRepository;
import boardcafe.boardpractice.member.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static boardcafe.boardpractice.common.exception.ErrorCode.NOT_FOUND_USER_ID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberNicknameResponse getNicknameById(final Long memberId) {
        return memberRepository.findNicknameById(memberId)
            .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_USER_ID));
    }
}
