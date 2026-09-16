package org.com.lab.services;

import org.com.lab.dto.request.LoginRequest;
import org.com.lab.dto.request.RegisterRequest;
import org.com.lab.dto.response.LoginResponse;
import org.com.lab.entity.User;
import org.com.lab.entity.enums.UserRole;
import org.com.lab.entity.enums.UserStatus;
import org.com.lab.error.LabErrorCode;
import org.com.lab.repository.UserJpaRepository;
import org.example.javaframework.infra.security.JwtProvider;
import org.example.javaframework.web.common.EnumConverter;
import org.example.javaframework.web.exception.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    private final UserJpaRepository userJpaRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public AuthServices(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public void register(RegisterRequest registerRequest) {
        String username = registerRequest.username();
        String email = registerRequest.email();
        String password = registerRequest.password();
        String role = registerRequest.role();
        if(!userJpaRepository.existsByUsername(username)) {
            throw new BusinessException(LabErrorCode.USER_ALREADY_EXISTS, username);
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(EnumConverter.fromString(UserRole.class, role));
        user.setStatus(UserStatus.ACTIVE);
        userJpaRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();
        User user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(LabErrorCode.USER_NOT_FOUND, username));
        if(!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BusinessException(LabErrorCode.INVALID_CREDENTIALS);
        }
        String token = jwtProvider.generateToken(user.getId(),user.getUsername(),user.getRole().toString(), null);
        return new LoginResponse(token);
    }

}
