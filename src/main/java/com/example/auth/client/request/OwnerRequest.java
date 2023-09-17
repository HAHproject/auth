package com.example.auth.client.request;

import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerRequest {

    private UUID id;
    private String email;
    private String username;
    private Role role;


}
