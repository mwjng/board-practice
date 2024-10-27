package boardcafe.boardpractice.todo.domain;

import boardcafe.boardpractice.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private Boolean completed = Boolean.FALSE;

    private Boolean deleted = Boolean.FALSE;

    public Todo(final String content) {
        this.content = content;
    }

    public void updateCompleted() {
        this.completed = !completed;
    }

    public void removeTodo() {
        this.deleted = true;
    }
}
