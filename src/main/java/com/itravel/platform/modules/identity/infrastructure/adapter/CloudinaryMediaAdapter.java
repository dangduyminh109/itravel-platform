package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itravel.platform.common.exceptions.UploadFailedException;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryMediaAdapter implements MediaUploadPort {

    private final Cloudinary cloudinary;

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder", "itravel-platform"
            ));
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new UploadFailedException();
        }
    }
}