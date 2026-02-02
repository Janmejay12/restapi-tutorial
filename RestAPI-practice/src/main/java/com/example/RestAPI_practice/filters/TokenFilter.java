package com.example.RestAPI_practice.filters;

import com.example.RestAPI_practice.entities.User;
import com.example.RestAPI_practice.repos.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    public TokenFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null && token.startsWith("DUMMY-")) {
            Long userId = Long.parseLong(token.substring(6));
            User user = userRepository.findById(userId)
                    .orElse(null);
            if(user != null){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        user,null, List.of()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return null;
        return authHeader.substring(7);
    }

}
