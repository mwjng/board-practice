package boardcafe.boardpractice.image.application;

import boardcafe.boardpractice.image.application.response.ImageResponse;
import boardcafe.boardpractice.image.domain.Image;
import boardcafe.boardpractice.image.infrastructure.ImageUploader;
import boardcafe.boardpractice.image.infrastructure.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageUploader uploader;

    public ImageResponse save(final MultipartFile image) {
        final String storedImage = uploader.uploadImage(image);
        imageRepository.save(new Image(storedImage));
        return new ImageResponse(storedImage);
    }
}
