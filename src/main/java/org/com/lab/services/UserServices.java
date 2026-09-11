package org.com.lab.services;

import lombok.extern.slf4j.Slf4j;
import org.com.lab.dto.response.UserInfoResponse;
import org.com.lab.entity.Domain;
import org.com.lab.entity.User;
import org.com.lab.entity.UserDomain;
import org.com.lab.error.LabErrorCode;
import org.com.lab.repository.UserDomainJpaRepository;
import org.com.lab.repository.UserJpaRepository;
import org.example.javaframework.infra.SessionService;
import org.example.javaframework.infra.model.UserInfo;
import org.example.javaframework.infra.security.CurrentUserContext;
import org.example.javaframework.web.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class UserServices {

    private final SessionService sessionService;

    private final UserJpaRepository userJpaRepository;

    private final UserDomainJpaRepository userDomainJpaRepository;

    public  UserServices(SessionService sessionService,  UserJpaRepository userJpaRepository,   UserDomainJpaRepository userDomainJpaRepository) {
        this.sessionService = sessionService;
        this.userJpaRepository = userJpaRepository;
        this.userDomainJpaRepository = userDomainJpaRepository;
    }

    public UserInfoResponse getUserInfo() {
        String accessToken = CurrentUserContext.get().accessToken();
        if(sessionService.findByAccessToken(accessToken).isPresent()){
            log.debug("User info found in redis cache");
            UserInfo userInfo = sessionService.findByAccessToken(accessToken).get();
            return UserInfoResponse.builder()
                    .username(userInfo.username())
                    .email(userInfo.email())
                    .fullName(userInfo.fullName())
                    .role(userInfo.role())
                    .build();
        }else {
            log.debug("User info not found in redis cache, fetching from database");
            return getUserInfoFromDb(accessToken);
        }
    }

    public void saveUserInfoToSession(String accessToken, UserInfoResponse userInfo) {
        log.debug("Saving information for user info in redis cache");
        UserInfo userInfo1 = new UserInfo(userInfo.username(), userInfo.email(), userInfo.fullName(), userInfo.role());
        sessionService.save(accessToken, userInfo1, Duration.ofMinutes(15));
    }

    public UserInfoResponse getUserInfoFromDb(String accessToken) {
        String userId = CurrentUserContext.get().userId();
        User user = userJpaRepository.findById(userId).orElseThrow(
                () -> new BusinessException(LabErrorCode.USER_NOT_FOUND));

        UserInfoResponse userInfo = UserInfoResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().toString())
                .build();

        saveUserInfoToSession(accessToken, userInfo);

        return userInfo;
    }

    public List<Long> getActiveDomainOfUser(String userId) {
        return userDomainJpaRepository.findByUserIdAndRevokedAtNull(userId)
                .stream()
                .map(UserDomain::getDomainId)
                .toList();
    }

}
