package com.project.Library_Management_Spring_BackEnd.controller;

import com.nimbusds.jose.JOSEException;
import com.project.Library_Management_Spring_BackEnd.dto.request.ApiResponse;
import com.project.Library_Management_Spring_BackEnd.dto.request.AuthenticationRequest;
import com.project.Library_Management_Spring_BackEnd.dto.request.IntrospectRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.AuthenticationResponse;
import com.project.Library_Management_Spring_BackEnd.dto.response.IntrospectResponse;
import com.project.Library_Management_Spring_BackEnd.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> logIn(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        ApiResponse apiResponse = new ApiResponse();
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        apiResponse.setResult(authenticationResponse);

        return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        ApiResponse apiResponse = new ApiResponse();
        IntrospectResponse introspectResponse = authenticationService.introspect(introspectRequest);

        apiResponse.setResult(introspectResponse);

        return apiResponse;
    }
}
