package com.oneclick.Oneclick.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageForm {

    private MultipartFile imageFile;
}
