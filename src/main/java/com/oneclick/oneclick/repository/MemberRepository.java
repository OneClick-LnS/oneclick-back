package com.oneclick.oneclick.repository;

import com.oneclick.oneclick.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
