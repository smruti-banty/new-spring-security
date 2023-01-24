package com.smruti.securitydemo.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] splitToken = header.split(" ");

            if (splitToken.length == 2 && "Bearer".equals(splitToken[0])) {
                try {
                    String token = splitToken[1];
                    DecodedJWT decoder = jwtConfig.verify(token);

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(decoder.getClaim("username"), 
                    null));

                } catch (Exception e) {

                }
            }
        }

        // private Authentication getAuthentication(String username, String role) {
        //     return new UsernamePasswordAuthenticationToken(username, String.class);
        // }
    }

}
