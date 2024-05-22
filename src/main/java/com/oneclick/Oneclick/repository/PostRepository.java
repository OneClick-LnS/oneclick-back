package com.oneclick.Oneclick.repository;

import com.oneclick.Oneclick.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.member.member_id = :memberId")
    List<Post> findByMemberId(Long memberId);

}
