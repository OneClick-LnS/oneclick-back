package com.oneclick.Oneclick.repository;

import com.oneclick.Oneclick.domain.UploadImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadImageRepository extends JpaRepository<UploadImage, Long> {

    @Query("SELECT u FROM UploadImage u WHERE u.member.member_id = :memberId")
    List<UploadImage> findAllByMemberId(Long memberId);
}
