package com.example.ceertifications.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {

    private String username;

    private String email;

    private Set<String> role;

    private String password;
}
