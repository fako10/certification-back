package com.example.ceertifications.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailValidationRequest {

    private String username;
    private String validationCode;
    private String password;
}
