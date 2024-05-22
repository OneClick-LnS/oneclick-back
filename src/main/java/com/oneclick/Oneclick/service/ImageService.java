package com.oneclick.Oneclick.service;

import com.oneclick.Oneclick.domain.Image;
import com.oneclick.Oneclick.domain.Member;
import com.oneclick.Oneclick.domain.UploadImage;
import com.oneclick.Oneclick.file.FileStore;
import com.oneclick.Oneclick.repository.ImageRepository;
import com.oneclick.Oneclick.repository.UploadImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileStore fileStore;
    private final UploadImageRepository uploadImageRepository;

    public UploadImage storeImageFile(MultipartFile imageFile) throws IOException {
        return fileStore.storeFile(imageFile);
    }

    @Transactional
    public void saveImage(Long memberId, UploadImage uploadedImage) {
        Image image = new Image();
        image.setImageFile(uploadedImage);

        Member member = new Member();
        member.setMember_id(memberId);
        image.setMember(member);
        uploadedImage.setMember(member);

        imageRepository.save(image);
    }

    @Transactional
    public List<UploadImage> findAllImagesByMemberId(Long memberId) {
        return uploadImageRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public org.springframework.core.io.Resource loadImage(String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @Transactional
    public ResponseEntity<Resource> downloadImage(Long imageId) throws MalformedURLException {
        Image image = imageRepository.findByImageId(imageId);
        String storeFileName = image.getImageFile().getStoreImageName();
        String uploadFileName = image.getImageFile().getUploadImageName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "image; filename=\"" + encodedUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
