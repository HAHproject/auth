package com.example.auth.domain.response;

import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {


    private String id;
    private String email;
    private String username;

    private String role;
    private String status;


    public UserResponse(User user,String status){
        this.id = user.getId().toString();
        this.status = status;
        this.email = user.getEmail();
        this.role = user.getRole().name();
        this.username = user.getUsername();

    }

}
