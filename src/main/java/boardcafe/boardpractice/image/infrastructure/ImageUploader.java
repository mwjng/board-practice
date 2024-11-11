package boardcafe.boardpractice.image.infrastructure;

import boardcafe.boardpractice.image.exception.ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static boardcafe.boardpractice.common.exception.ErrorCode.FAIL_IMAGE_UPLOAD;

@Component
public class ImageUploader {

    private final String imagePath;

    public ImageUploader(@Value("${file.path}") final String imagePath) {
        this.imagePath = imagePath;
    }

    public String uploadImage(final MultipartFile image) {
        final String originalName = image.getOriginalFilename();
        final String path = getImageFullPath(originalName);
        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            throw new ImageUploadException(FAIL_IMAGE_UPLOAD);
        }
        return path;
    }

    private String getImageFullPath(final String originalName) {
        final String extension = getExtension(originalName);
        return imagePath + UUID.randomUUID().toString().substring(0, 8) + extension;
    }

    private String getExtension(final String originalName) {
        return originalName.substring(originalName.lastIndexOf("."));
    }
}
