package com.oneclick.Oneclick.controller;

import com.oneclick.Oneclick.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

}
