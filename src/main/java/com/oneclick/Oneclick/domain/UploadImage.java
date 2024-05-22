package com.oneclick.Oneclick.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UploadImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_id")
    private Long upload_id;

    private String uploadImageName;
    private String storeImageName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public UploadImage(String uploadFileName, String storeFileName) {
        this.uploadImageName = uploadFileName;
        this.storeImageName = storeFileName;
    }

    public UploadImage() {

    }


}
