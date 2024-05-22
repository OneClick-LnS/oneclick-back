package com.oneclick.Oneclick.controller;

import com.oneclick.Oneclick.domain.Post;
import com.oneclick.Oneclick.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/{member_id}/savedImages/uploadPost/{image_id}")
    public ResponseEntity<String> uploadPost(@PathVariable Long member_id, @PathVariable Long image_id) {
        postService.createPost(member_id, image_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{post_id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long post_id) {
        postService.LikePost(post_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{post_id}/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable Long post_id) {
        postService.UnlikePost(post_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{member_id}/getPosts")
    public List<Post> getPosts (@PathVariable Long member_id) {
        return postService.getPosts(member_id);
    }
}
