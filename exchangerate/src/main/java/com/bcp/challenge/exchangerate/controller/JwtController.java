package com.bcp.challenge.exchangerate.controller;

import com.bcp.challenge.exchangerate.model.JwtRequest;
import com.bcp.challenge.exchangerate.model.JwtResponse;
import com.bcp.challenge.exchangerate.model.UserModel;
import com.bcp.challenge.exchangerate.service.CustomUserDetailService;
import com.bcp.challenge.exchangerate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword());
            authenticationManager.authenticate(upat);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        String jwtToken = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(jwtToken);
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    public UserModel getCurrentUser(Principal principal) {
        UserDetails userDetails =  this.customUserDetailService.loadUserByUsername(principal.getName());
        return (UserModel) userDetails;
    }
}