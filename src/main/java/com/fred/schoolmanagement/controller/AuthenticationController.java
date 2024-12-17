package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.*;
import com.fred.schoolmanagement.service.implimentation.AuthenticationStudentServiceImplementation;
import com.fred.schoolmanagement.service.implimentation.ForgotPasswordServiceImplementation;
import com.fred.schoolmanagement.service.implimentation.LoginServiceImplementation;
import com.fred.schoolmanagement.service.implimentation.ResetPasswordServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/authentication")
public class AuthenticationController {
    private LoginServiceImplementation loginServiceImplementation;
    private ForgotPasswordServiceImplementation forgotPasswordServiceImplementation;
    private ResetPasswordServiceImplementation resetPasswordServiceImplementation;

    @Autowired
    public AuthenticationController(LoginServiceImplementation loginServiceImplementation, ForgotPasswordServiceImplementation forgotPasswordServiceImplementation, ResetPasswordServiceImplementation resetPasswordServiceImplementation) {
        this.loginServiceImplementation = loginServiceImplementation;
        this.forgotPasswordServiceImplementation = forgotPasswordServiceImplementation;
        this.resetPasswordServiceImplementation = resetPasswordServiceImplementation;
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO responseDTO = loginServiceImplementation.authenticateUser(loginDTO);
        if (responseDTO.getMessage().contains("successful")){
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }
    }

    @PostMapping(path = "/forgot-password")
    public ResponseEntity<ForgotPasswordResponseDTO> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
        //to check error
        ForgotPasswordResponseDTO forgotPasswordResponseDTO = forgotPasswordServiceImplementation.forgotPasswordResponseDTO(forgotPasswordDTO);

        if (forgotPasswordResponseDTO.getMessage().contains("successful")){
            return ResponseEntity.ok(forgotPasswordResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(forgotPasswordResponseDTO);
        }
    }

    @PutMapping(path = "/reset-password")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        ResetPasswordResponseDTO response = resetPasswordServiceImplementation.resetPasswordResponseDTO(resetPasswordDTO);
        return ResponseEntity.ok(response);
    }

    
}
