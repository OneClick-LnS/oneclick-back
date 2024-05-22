package com.oneclick.Oneclick.service;

import com.oneclick.Oneclick.domain.Member;
import com.oneclick.Oneclick.domain.Post;
import com.oneclick.Oneclick.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


}
