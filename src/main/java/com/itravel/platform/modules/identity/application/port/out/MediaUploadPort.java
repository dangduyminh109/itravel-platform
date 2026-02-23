package com.itravel.platform.modules.identity.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface MediaUploadPort {
    String uploadAvatar(MultipartFile file);
}
