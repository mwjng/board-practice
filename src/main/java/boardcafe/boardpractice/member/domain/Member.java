package boardcafe.boardpractice.member.domain;

import boardcafe.boardpractice.common.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static boardcafe.boardpractice.member.domain.MemberStatus.ACTIVE;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    private String name;

    private String email;

    @Column(nullable = false, unique = true)
    private Long userId;

    private String imageUrl;

    @Enumerated(STRING)
    private MemberStatus status;

    @Column(nullable = false)
    private LocalDateTime lastLogin;

    @Builder
    public Member(
        final String nickname,
        final String name,
        final String email,
        final Long userId,
        final String imageUrl
    ) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.status = ACTIVE;
        this.lastLogin = now();
    }

    public void updateLoginDate(LocalDateTime loginDateTime) {
        this.lastLogin = loginDateTime;
    }
}
