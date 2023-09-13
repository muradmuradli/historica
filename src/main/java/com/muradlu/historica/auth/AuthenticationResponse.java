package com.muradlu.historica.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muradlu.historica.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("user")
    private User user;
}