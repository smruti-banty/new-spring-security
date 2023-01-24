package com.smruti.securitydemo.jwt;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.PostConstruct;

@Component
public class JwtConfig {

    private final String secret = "smruti";
    private final String issuer = "myapp.com";

    public String getToken(String username, String role) {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("username", username)
                .withClaim("role", role)
                .sign(getAlgorithm());

    }

    public DecodedJWT verify(String token) throws Exception {
        JWTVerifier verifier = JWT.require(getAlgorithm()).withIssuer(token).build();
        return verifier.verify(token);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC384(secret);
    }
}
