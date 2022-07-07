package com.bcp.challenge.exchangerate.controller;

import com.bcp.challenge.exchangerate.model.JwtRequest;
import com.bcp.challenge.exchangerate.model.JwtResponse;
import com.bcp.challenge.exchangerate.model.UserModel;
import com.bcp.challenge.exchangerate.model.UserResponse;
import com.bcp.challenge.exchangerate.service.CustomUserDetailService;
import com.bcp.challenge.exchangerate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserModel userModel){
        UserModel userModel1 = customUserDetailService.register(userModel);
        ResponseEntity<UserModel> re = new ResponseEntity<>(userModel1, HttpStatus.CREATED);
        return re;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword());
            authenticationManager.authenticate(upat);
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        UserModel userModel = (UserModel) userDetails;
        UserResponse userResponse = new UserResponse(userModel.getId(), userModel.getUsername(), userModel.getFirstName(), userModel.getLastName());

        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .body(userResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/currentUser")
    public ResponseEntity<UserResponse> getCurrentUser(Principal principal) {
        UserDetails userDetails =  this.customUserDetailService.loadUserByUsername(principal.getName());
        UserModel userModel = (UserModel) userDetails;
        UserResponse userResponse = new UserResponse(userModel.getId(), userModel.getUsername(), userModel.getFirstName(), userModel.getLastName());

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
