package com.decasecure.decsecure.service.implementation;

import com.decasecure.decsecure.dto.request.LoginRequest;
import com.decasecure.decsecure.dto.request.RegistrationRequest;
import com.decasecure.decsecure.dto.response.ApiResponse;
import com.decasecure.decsecure.dto.response.LoginResponse;
import com.decasecure.decsecure.enitity.AppUser;
import com.decasecure.decsecure.exceptions.CustomException;
import com.decasecure.decsecure.repository.AppUserRepo;
import com.decasecure.decsecure.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

//    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final AppUserRepo appUserRepo;

    @Override
    public ApiResponse<LoginResponse> loginUser(LoginRequest request) {

        //Login --> /login --> Authenticate(check details) -->

        // Jwt -- jjt encode secret + email
        // Generate and Token and Send to User

        return null;
    }

    @Override
    public ApiResponse<String> registerUser(RegistrationRequest request) {
        log.info("request to register user");
        // Normal save
        // Encrypt Password
        // Save
        // check if user exist
        // Yes --> user exist, please
        Boolean doesUserExist = appUserRepo.existsByEmail(request.getEmail());

        if (doesUserExist) {
            throw new CustomException("You Already Exist, Please Login", HttpStatus.CONFLICT);
        }

        AppUser newUser = AppUser.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        appUserRepo.save(newUser);
        // No --> Save the person -- User Saved Successfully


        return ApiResponse.<String>builder()
                .status(true)
                .message("Congratulations, Registered Successfully")
                .build();
    }

    @Override
    public ApiResponse<String> paySalary() {
        log.info("This is a Protected House");
        return ApiResponse.<String>builder()
                .message("This is a Protected House")
                .build();
    }
}
