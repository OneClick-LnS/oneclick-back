package com.oneclick.Oneclick.repository;

import com.oneclick.Oneclick.domain.Image;
import com.oneclick.Oneclick.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
