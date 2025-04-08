package com.saitdundar.service;

import com.saitdundar.dto.AuthRequest;
import com.saitdundar.dto.AuthResponse;
import com.saitdundar.dto.DtoUser;
import com.saitdundar.dto.RefreshTokenRequest;

public interface IAuthenticationService {


    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);

}
