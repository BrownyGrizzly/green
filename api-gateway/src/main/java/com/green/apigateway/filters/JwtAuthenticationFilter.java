package com.green.apigateway.filters;

import com.green.apigateway.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;

@Component
@Order(1)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Extract Authorization header
            String authorizationHeader = request.getHeader("Authorization");

            // Check if the header is present and starts with "Bearer "
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extract JWT token from Authorization header
                String jwtToken = authorizationHeader.substring(7);

                // Validate the JWT token
                if (jwtUtil.validateJwtToken(jwtToken)) {
                    // If token is valid, continue with the filter chain
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            // If token is not valid, return unauthorized status
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        } catch (Exception e) {
            // Handle any exceptions that might occur
            throw new ServletException("Authentication failed: " + e.getMessage(), e);
        }
    }
}
