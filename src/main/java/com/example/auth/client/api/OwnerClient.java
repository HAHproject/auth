package com.example.auth.client.api;

import com.example.auth.client.request.OwnerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("OWNER-SERVICE")
public interface OwnerClient {
    @PostMapping("/api/v1/owner/signup")
    ResponseEntity<Void> saveOwner(@RequestBody OwnerRequest request);
}