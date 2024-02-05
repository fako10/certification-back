package com.example.ceertifications.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private boolean locked;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, boolean locked) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.locked = locked;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

}
