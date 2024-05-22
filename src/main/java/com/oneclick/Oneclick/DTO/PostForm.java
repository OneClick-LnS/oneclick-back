package com.oneclick.Oneclick.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostForm {

    private MultipartFile imageFile;

}
