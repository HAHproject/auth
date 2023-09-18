package com.example.auth.client.api;

import com.example.auth.client.request.CustomerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerClient {
    @PostMapping("/api/v1/customer/signup")
    ResponseEntity<Void> saveCustomer(@RequestBody CustomerRequest request);

}