package com.example.finalproject.service.impl;

import com.example.finalproject.enums.Role;
import com.example.finalproject.exception.AlreadyExistsException;
import com.example.finalproject.exception.NotFoundException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private final String myEmail = "cyberpunkalert@mail.ru";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();

    @Override
    public void register(UserRequestDto userReqDto){
        log.info("ActionLog.register.started: username {}", userReqDto.getUsername());


        if( userRepository.findByUsername(userReqDto.getUsername()).isPresent() ){
            log.warn("ActionLog.register.usernameAlreadyExists: username {}", userReqDto.getUsername());
            throw new AlreadyExistsException("Username is already taken.");
        }

        User user = new User();
        user.setUsername(userReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setEmail(userReqDto.getEmail());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        WishList wishList=new WishList();
        wishList.setUser(user);
        user.setWishList(wishList);

        userRepository.save(user);

        log.info("ActionLog.register.completed: username {}", userReqDto.getUsername());
    }

    @Override
    public LoginResponse login(LoginReq loginReq){
        log.info("ActionLog.login.started: username {}", loginReq.getUsername());

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        String token = jwtService.createToken(new User(loginReq.getUsername()));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUsername(loginReq.getUsername());


        log.info("ActionLog.login.success: username {}", loginReq.getUsername());
        return loginResponse;
    }

    @Override
    public void forgetPassword(String email) {
        log.info("ActionLog.resetPasswordOtp.started: email {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User email not found"));


        String otp = String.format("%04d", new Random().nextInt(10000));
        log.info("Generated OTP for {}: {}", email, otp);


        otpStorage.put(email, otp);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom(myEmail);
        msg.setSubject("Code Verification");
        msg.setText("Dear " + user.getUsername() + ",\n\nYour OTP for password reset is: " + otp +
                "\n\nThis OTP will expire in 10 minutes.\n\nRegards,\nPharmacy.az");

        try {
            javaMailSender.send(msg);
            log.info("Successfully sent email to {}", email);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", email, e.getMessage());
            throw new RuntimeException("Failed to send email. Please try again later.");
        }
    }



    @Override
    public void resetPassword(String email, String newPassword, String otp) {
        log.info("ActionLog.resetPassword.started: email {}", email);

        String storedOtp = otpStorage.get(email);
        if (storedOtp == null) {
            log.warn("No OTP found for {}", email);
            throw new RuntimeException("No OTP found for this email");
        }

        if (!storedOtp.equals(otp)) {
            log.warn("Invalid OTP for {}", email);
            throw new RuntimeException("Invalid OTP");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User email not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        otpStorage.remove(email);

        log.info("ActionLog.resetPassword.completed: email {}", email);
    }




}