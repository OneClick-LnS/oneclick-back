package com.oneclick.oneclick.controller;

import com.oneclick.oneclick.domain.Member;
import com.oneclick.oneclick.domain.MemberRole;
import com.oneclick.oneclick.dto.*;
import com.oneclick.oneclick.repository.MemberRepository;
import com.oneclick.oneclick.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    @GetMapping("/name")
    public String name() {
        return "내이름";
    }

    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        return memberService.login(loginDTO);
    }
}

