package com.oneclick.Oneclick.controller;

import com.oneclick.Oneclick.DTO.ImageForm;
import com.oneclick.Oneclick.domain.UploadImage;
import com.oneclick.Oneclick.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

//    private final ImageRepository imageRepository;
//    private final FileStore fileStore;
//    private final UploadImageRepository uploadImageRepository;
//
//    @GetMapping("/{member_id}/createImage/upload")
//    public String newImage(@ModelAttribute ImageForm imageForm) {
//        return "image-form";
//    }
//
//    @PostMapping("/{member_id}/createImage/upload")
//    public String saveImage(@PathVariable Long member_id,
//                            @RequestParam("imageFile") MultipartFile imageFile,
//                            RedirectAttributes redirectAttributes) throws IOException {
//
//        ImageFile uploadedImage = fileStore.storeFile(imageFile);
//
//        Image image = new Image();
//        image.setImageFile(uploadedImage);
//
//        Member member = new Member();
//        member.setMember_id(member_id);
//        image.setMember(member);
//        uploadedImage.setMember(member);
//
//        imageRepository.save(image);
//
//        redirectAttributes.addAttribute("member_id", member.getMember_id());
//        return "redirect:/{member_id}/createImage";
//    }
//
//    @GetMapping("/{member_id}/savedImages")
//    public String savedImages(@PathVariable Long member_id, Model model) {
//        List<ImageFile> imageFiles = uploadImageRepository.findAllByMemberId(member_id);
//        model.addAttribute("images", imageFiles);
//        return "image-view";
//    }
//
//    @ResponseBody
//    @GetMapping("/getImages/{filename:.+}")
//    public Resource getImages(@PathVariable String filename) throws MalformedURLException {
//        return new UrlResource("file:" + fileStore.getFullPath(filename));
//    }
//
//    @GetMapping("/savedImages/download/{image_id}")
//    public ResponseEntity<Resource> downloadImage(@PathVariable Long image_id) throws MalformedURLException {
//        Image image = imageRepository.getOne(image_id);
//        String storeFileName = image.getImageFile().getStoreImageName();
//        String uploadFileName = image.getImageFile().getUploadImageName();
//
//        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
//
//        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
//        String contentDisposition = "image; filename=\"" + encodedUploadFileName + "\"";
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//                .body(urlResource);
//    }

    private final ImageService imageService;

    @GetMapping("/{member_id}/createImage/upload")
    public String newImage(@ModelAttribute ImageForm imageForm) {
        return "image-form";
    }

    @PostMapping("/{member_id}/createImage/upload")
    public String saveImage(@PathVariable Long member_id,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            RedirectAttributes redirectAttributes) throws IOException {

        UploadImage uploadedImage = imageService.storeImageFile(imageFile);
        imageService.saveImage(member_id, uploadedImage);

        redirectAttributes.addAttribute("member_id", member_id);
        return "redirect:/{member_id}/createImage";
    }

    @GetMapping("/{member_id}/savedImages")
    public String savedImages(@PathVariable Long member_id, Model model) {
        List<UploadImage> imageFiles = imageService.findAllImagesByMemberId(member_id);
        model.addAttribute("images", imageFiles);
        return "image-view";
    }

    @ResponseBody
    @GetMapping("/getImages/{filename:.+}")
    public Resource getImages(@PathVariable String filename) throws MalformedURLException {
        return imageService.loadImage(filename);
    }

    @GetMapping("/savedImages/download/{image_id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long image_id) throws MalformedURLException {
        return imageService.downloadImage(image_id);
    }
}
