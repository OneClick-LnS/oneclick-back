package com.oneclick.Oneclick.service;

import com.oneclick.Oneclick.domain.Image;
import com.oneclick.Oneclick.domain.Member;
import com.oneclick.Oneclick.domain.Post;
import com.oneclick.Oneclick.repository.ImageRepository;
import com.oneclick.Oneclick.repository.MemberRepository;
import com.oneclick.Oneclick.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void createPost(Long member_id, Long image_id) {
        Member member = memberRepository.findById(member_id).orElse(null);
        Image image = imageRepository.findById(image_id).orElse(null);

        Post post = new Post();
        post.setMember(member);
        post.setLikes(0);
        post.setImage(image);
        postRepository.save(post);
    }

    @Transactional
    public void LikePost(long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));
        post.incrementLikes();
    }

    @Transactional
    public void UnlikePost(long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다."));
        post.decrementLikes();
    }

    @Transactional
    public List<Post> getPosts(Long member_id) {
        return postRepository.findByMemberId(member_id);
    }
}
