package com.oneclick.oneclick.service;

import com.oneclick.oneclick.dto.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);

    ResponseEntity login(LoginDTO loginDTO);
}
