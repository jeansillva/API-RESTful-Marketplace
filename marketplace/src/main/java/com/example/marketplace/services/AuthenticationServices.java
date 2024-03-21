package com.example.marketplace.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.marketplace.dtos.AuthDto;
import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.repositories.ClienteRepository;

@Service
public class AuthenticationServices implements UserDetailsService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clienteRepository.findByEmail(email);
    }

    public String obterTokenJWT(AuthDto authDto){
        ClienteModel clienteModel = clienteRepository.findByEmail(authDto.email());
        return generateTokenJWT(clienteModel);
    }

    public String generateTokenJWT(ClienteModel clienteModel){
        try{
            Algorithm algorithm = Algorithm.HMAC256("chave-passe");
            return JWT.create()
                .withIssuer("marketplace")
                .withSubject(clienteModel.getEmail())
                .withExpiresAt(geraDataExpiracao())
                .sign(algorithm);

        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token!"+e.getMessage());
        }
    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now()
        .plusHours(8)
        .toInstant(ZoneOffset.of("-03:00"));
    }
    
}
