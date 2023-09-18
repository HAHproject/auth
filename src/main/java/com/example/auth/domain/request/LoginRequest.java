package com.example.auth.domain.request;


import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String email;
    private String password;


}
