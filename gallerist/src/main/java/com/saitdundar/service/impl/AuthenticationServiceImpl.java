package com.saitdundar.service.impl;

import com.saitdundar.dto.AuthRequest;
import com.saitdundar.dto.AuthResponse;
import com.saitdundar.dto.DtoUser;
import com.saitdundar.dto.RefreshTokenRequest;
import com.saitdundar.exception.BaseException;
import com.saitdundar.exception.ErrorMessage;
import com.saitdundar.exception.MessageType;
import com.saitdundar.jwt.JWTService;
import com.saitdundar.model.RefreshToken;
import com.saitdundar.model.User;
import com.saitdundar.repository.RefreshTokenRepository;
import com.saitdundar.repository.UserRepository;
import com.saitdundar.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private User createUser(AuthRequest input){
        User user=new User();
        user.setCreateTime(new java.util.Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return user;
    }

    @Override
    public DtoUser register(AuthRequest input) {

        DtoUser user=new DtoUser();
        User savedUser= userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser,user);
        return user;
    }

    private RefreshToken crateRefreshToken(User user){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optional= userRepository.findByUsername(input.getUsername());

            String accessToken =jwtService.generateToken(optional.get());

            RefreshToken savedRefreshToken= refreshTokenRepository.save(crateRefreshToken(optional.get()));

            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

        }catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isValidRefreshToken(Date expireDate){
        return new Date().before(expireDate);
    }


    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());

        if(optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
        }
        if(!isValidRefreshToken(optional.get().getExpireDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
        }
        User user=optional.get().getUser();
        String accessToken =jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(crateRefreshToken(user));
        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }
}


