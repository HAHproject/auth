package com.example.auth.service;

import com.example.auth.client.api.CustomerClient;
import com.example.auth.client.api.OwnerClient;
import com.example.auth.client.request.CustomerRequest;
import com.example.auth.client.request.OwnerRequest;
import com.example.auth.config.JwtService;
import com.example.auth.config.TokenInfo;
import com.example.auth.controller.AuthController;
import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import com.example.auth.domain.request.LoginRequest;
import com.example.auth.domain.request.SignupRequest;
import com.example.auth.domain.response.AuthResponse;
import com.example.auth.domain.response.UserResponse;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomerClient customerClient;
    private final OwnerClient ownerClient;



    public AuthResponse check(TokenInfo tokenInfo){
       String email =  tokenInfo.getEmail();

        User userByEmail = userRepository.findByEmail(email);
        String urlRedirect;

        if(userByEmail == null){

            urlRedirect  = "/signup-auth"; // 여기에 우리 서버 정보 입력하는 곳으로 보내야함.
            //이건 유저가 없으니깐 redirect로 추가 정보를 입력하는 곳으로 보내야한다.
            return new AuthResponse(urlRedirect,null);
        };
        // 이 밑은 유저가 있으므로, 토큰을 만들어서 메인으로 보내줘야한다.
        String status = "normal";
    if(tokenInfo.getRole().equals(Role.OWNER.name())) {
        status = ownerClient.getOwner(email);
    }
        String token = jwtService.makeToken(userByEmail, status);



        urlRedirect = "/main";

        return new AuthResponse(urlRedirect,token);
    }


   @Transactional
    public UserResponse signup(SignupRequest request) {
       // 일단 여기서 해야하는 행동은 가입이 없다는 것을 확인하여 추가 정보를 가져온 상황이기 때문에..?
       // 내 생각엔... 모르겟다!

       User user = userRepository.save(request.toEntity());
       // 가입을 시키고,


       Role role = request.getRole();


       if (role == Role.CUSTOMER) {
           customerClient.saveCustomer(new CustomerRequest(
                   user.getId(), user.getEmail(), user.getUsername(), user.getRole()
           ));

       } else if (role == Role.OWNER) {

           ownerClient.saveOwner(new OwnerRequest(
                   user.getId(), user.getEmail(), user.getUsername()
           ));
       }


       return new UserResponse(user,"APPLY");
   }



    public User login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail());
        // 이건 나중에 수정하자 ... 일단 패스워드 없이 가져와
        // 이유 : user에 대한 entity를 바꾸는 사이드 이펙트를 현재 감당할 수 없음.

        return user;

    }
}
