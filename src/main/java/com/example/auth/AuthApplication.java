package com.example.auth;

import com.example.auth.domain.dto.CustomUrl;
import com.example.auth.domain.entity.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	@Bean
	public Map<Role, CustomUrl> urlList() {
		Map<Role, CustomUrl> map = new HashMap<>();
		map.put(Role.CUSTOMER, new CustomUrl("http://localhost:8000", "http://localhost:8000/customer"));
		map.put(Role.OWNER, new CustomUrl("http://localhost:8000", "http://localhost:8000/owner"));
		return map;
	}

}
