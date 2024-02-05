package com.example.ceertifications.security;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {

    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "ngadene@gmail.com";
        String password = "Rivaldo@10";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length   () > 0)) {
            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}
