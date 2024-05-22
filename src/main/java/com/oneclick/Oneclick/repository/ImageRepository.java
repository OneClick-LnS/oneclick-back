package com.oneclick.Oneclick.repository;

import com.oneclick.Oneclick.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i FROM Image i WHERE i.image_id = :imageId")
    Image findByImageId(Long imageId);
}
