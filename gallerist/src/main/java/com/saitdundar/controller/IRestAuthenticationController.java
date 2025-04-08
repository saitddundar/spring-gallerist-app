package com.saitdundar.controller;

import com.saitdundar.dto.AuthRequest;
import com.saitdundar.dto.AuthResponse;
import com.saitdundar.dto.DtoUser;
import com.saitdundar.dto.RefreshTokenRequest;
import com.saitdundar.model.RefreshToken;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
