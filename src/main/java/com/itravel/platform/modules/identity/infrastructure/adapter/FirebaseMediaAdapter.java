package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.itravel.platform.common.exceptions.ErrorCode;
import com.itravel.platform.common.exceptions.FirebaseException;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseMediaAdapter implements MediaUploadPort {

    @Value("${firebase.storage.bucket-name}")
    private String bucketName;

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();

            String fileName = "avatars/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

            bucket.create(fileName, file.getBytes(), file.getContentType());

            // Ép kiểu URL để sinh ra link public có thể xem trực tiếp trên trình duyệt
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                    bucketName, encodedFileName);

        } catch (IOException e) {
            throw new FirebaseException(ErrorCode.FIREBASE_UPLOAD_FAILED);
        }
    }
}