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
public class SignupRequest {

    private UUID id;
    private String email;
    private String username;
    private Role role;


public User toEntity(){
    return  User.builder()
            .id(id)
            .email(email)
            .role(role)
            .username(username)
            .build();

}
}
