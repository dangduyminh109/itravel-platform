package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourImageRequest {
        @NotNull(message = "TOUR_IMAGE_FILE_CANNOT_BE_NULL")
        MultipartFile image;
        @NotNull(message = "TOUR_IMAGE_THUMBNAIL_CANNOT_BE_NULL")
        Boolean isThumbnail;
}