package com.oneclick.Oneclick.file;

import com.oneclick.Oneclick.domain.UploadImage;
import com.oneclick.Oneclick.repository.UploadImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    private final UploadImageRepository uploadImageRepository;

    @Autowired
    public FileStore(UploadImageRepository uploadImageRepository) {
        this.uploadImageRepository = uploadImageRepository;
    }

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    //추후 여러개의 이미지 업로드 기능 추가를 위한 코드
    public List<UploadImage> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadImage> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile)); //파일의 이름 정보가 들어간 UploadFile 객체를 storeFileResult에 넣어줌
            }
        }
        return storeFileResult;

    }

    public UploadImage storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        UploadImage uploadImage = new UploadImage(originalFileName, storeFileName);
        uploadImageRepository.save(uploadImage);
        
        return uploadImage;
    }

    private String createStoreFileName(String originalFileName) {

        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;

    }

    private String extractExt(String originalFileName) {

        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1);

    }
}
