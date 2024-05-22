package com.oneclick.Oneclick.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long member_id;

    private String name;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<UploadImage> imageFiles = new ArrayList<>();
}
