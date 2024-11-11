package boardcafe.boardpractice.image.presentation;

import boardcafe.boardpractice.image.application.ImageService;
import boardcafe.boardpractice.image.application.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/image")
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> upload(@RequestPart final MultipartFile image) {
        return ResponseEntity.status(OK).body(imageService.save(image));
    }
}
