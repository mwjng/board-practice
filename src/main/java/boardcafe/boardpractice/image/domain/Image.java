package boardcafe.boardpractice.image.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
public class Image {

    private static final AtomicLong idGenerator = new AtomicLong();

    private Long id;
    private String imgPath;

    public Image(final String imgPath) {
        this.id = idGenerator.incrementAndGet();
        this.imgPath = imgPath;
    }
}
