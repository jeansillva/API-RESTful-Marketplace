package com.example.marketplace.resources;

import com.example.marketplace.dtos.AuthDto;
import com.example.marketplace.services.AuthenticationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationResources {
    

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDto authDto){

        var authToken = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.senha());
        authenticationManager.authenticate(authToken);

        return authenticationServices.obterTokenJWT(authDto);
    }
}
