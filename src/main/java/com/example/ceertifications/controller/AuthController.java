package com.example.ceertifications.controller;

import com.example.ceertifications.dto.ExamenGroupDto;
import com.example.ceertifications.dto.enums.EmailSubject;
import com.example.ceertifications.entities.UserRole;
import com.example.ceertifications.entities.Users;
import com.example.ceertifications.payload.request.EmailValidationRequest;
import com.example.ceertifications.payload.request.LoginRequest;
import com.example.ceertifications.payload.request.SignupRequest;
import com.example.ceertifications.payload.response.JwtResponse;
import com.example.ceertifications.payload.response.MessageResponse;
import com.example.ceertifications.repositories.UserRepository;
import com.example.ceertifications.security.jwt.JwtUtils;
import com.example.ceertifications.security.service.UserDetailsImpl;
import com.example.ceertifications.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.isLocked()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Users user = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        if(strRoles == null ){
            user.setUserRole(UserRole.USER);
        }
        else{
            user.setUserRole(UserRole.valueOf(strRoles.stream().findFirst().get()));
        }
        try {
            String validationCode = userService.generateRandomAlphanumericString();
            String mailBody = "Votre code de confirmation est le : " + validationCode;
            String subject = "Code de validation";
            //userService.sendEmail(user.getEmail(), subject, mailBody);
            userService.sendEmail(user.getEmail(), validationCode, EmailSubject.VALIDATION_CODE);
            user.setLocked(true);
            user.setValidationcode(validationCode);
            userRepository.save(user);
            //} catch (MessagingException | UnsupportedEncodingException e) {
        } catch(Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: invalid email adress!"));
        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/emailvalidation")
    public ResponseEntity<?> registerUser( @RequestBody EmailValidationRequest emailValidationRequest) {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailValidationRequest.getUsername(), emailValidationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Users user = userRepository.findByUsername(emailValidationRequest.getUsername()).orElse(null);
        if(emailValidationRequest.getValidationCode().equals(user.getValidationcode())) {
            user.setLocked(false);
            userRepository.save(user);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles,
                    userDetails.isLocked()));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: invalid validation code!"));
        }
    }

    @PostMapping("/changepwd")
    public ResponseEntity<?> getExamenGroupByCertificationId(@RequestBody String userEmail) throws UnsupportedEncodingException, MessagingException {
        if (!userRepository.existsByEmail(userEmail)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: there is no account with this email!"));
        } else {
            Users user = userRepository.findByEmail(userEmail).orElse(null);
            if(user != null) {
                String generatedPwd = userService.generateRandomAlphanumericString();
                userService.sendEmail(userEmail, generatedPwd, EmailSubject.CHANGE_PWD);
                return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: there is no account with this email!"));
            }
        }

    }
}
