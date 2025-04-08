package com.saitdundar.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
public class AuthRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;






}
