package boardcafe.boardpractice.image.infrastructure;

import boardcafe.boardpractice.image.domain.Image;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ImageRepository {

    private final ConcurrentHashMap<Long, Image> store = new ConcurrentHashMap<>();

    public Image save(final Image image) {
        store.put(image.getId(), image);
        return image;
    }
}
