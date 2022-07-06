package com.bcp.challenge.exchangerate.filter;

import com.bcp.challenge.exchangerate.service.CustomUserDetailService;
import com.bcp.challenge.exchangerate.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = httpServletRequest.getHeader("Authorization");
        if(header == null || header.isEmpty() || !header.startsWith("Bearer ")){
            System.out.println("Invalid Bearer Token Format!!");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = header.substring(7);

        try {
            String username = jwtUtil.extractUsername(token);

            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            /*if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(upat);
            } else {
                System.out.println("Invalid Token!!");
            }*/
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails == null ? List.of() : userDetails.getAuthorities());

            upat.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
            );

            SecurityContextHolder.getContext().setAuthentication(upat);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
