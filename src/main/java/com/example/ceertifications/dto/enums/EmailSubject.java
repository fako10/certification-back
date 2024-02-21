package com.example.ceertifications.dto.enums;

public enum EmailSubject {

    VALIDATION_CODE("email validation code","Your email validation code is : "),
    CHANGE_PWD("new password","Your new password is : ");

    public final String subject;
    public final String body;


    private EmailSubject(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
