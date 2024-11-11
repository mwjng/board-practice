package boardcafe.boardpractice.member.domain.repository;

import boardcafe.boardpractice.member.application.response.MemberNicknameResponse;
import boardcafe.boardpractice.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.userId = :userId and m.status != 'DELETED'")
    Optional<Member> findByUserId(final Long userId);

    @Query("select new boardcafe.boardpractice.member.application.response.MemberNicknameResponse" +
        "(m.nickname) from Member m where m.id = :memberId and m.status = 'ACTIVE'")
    Optional<MemberNicknameResponse> findNicknameById(final Long memberId);
}
