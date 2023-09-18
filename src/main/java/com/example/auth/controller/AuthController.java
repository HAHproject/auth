package com.example.auth.controller;

import com.example.auth.config.TokenInfo;
import com.example.auth.domain.entity.User;
import com.example.auth.domain.request.LoginRequest;
import com.example.auth.domain.request.SignupRequest;
import com.example.auth.domain.response.AuthResponse;
import com.example.auth.domain.response.UserResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/check")
    public AuthResponse check(@AuthenticationPrincipal TokenInfo tokenInfo){
        return authService.check(tokenInfo);
    }



    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
//

    //오너랑 아예 경로가 다르게 와야겠는데?
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignupRequest request){
        return authService.signup(request);
    }

    // 일단 이 두메소드는 사용하지 않는 것으로 하자.

    @GetMapping("/me")
    public TokenInfo me(@AuthenticationPrincipal TokenInfo tokenInfo){
        return tokenInfo;
    }
}
