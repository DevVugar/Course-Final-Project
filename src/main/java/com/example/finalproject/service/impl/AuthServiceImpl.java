package com.example.finalproject.service.impl;

import com.example.finalproject.enums.Role;
import com.example.finalproject.exception.AlreadyExistsException;
import com.example.finalproject.jwt.JwtService;
import com.example.finalproject.model.dto.request.LoginReq;
import com.example.finalproject.model.dto.request.UserRequestDto;
import com.example.finalproject.model.dto.response.LoginResponse;
import com.example.finalproject.model.entity.User;
import com.example.finalproject.model.entity.WishList;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public void register(UserRequestDto userReqDto){
        if( userRepository.findByUsername(userReqDto.getUsername()).isPresent() ){
            throw new AlreadyExistsException("Username is already taken.");
        }

        User user = new User();
        user.setUsername(userReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setFullName(userReqDto.getFullName());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        WishList wishList=new WishList();
        wishList.setUser(user);
        user.setWishList(wishList);

        userRepository.save(user);

    }

    @Override
    public LoginResponse login(LoginReq loginReq){

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        String token = jwtService.createToken(new User(loginReq.getUsername()));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUsername(loginReq.getUsername());
        return loginResponse;
    }


}