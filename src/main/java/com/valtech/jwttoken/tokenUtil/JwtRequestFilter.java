package com.valtech.jwttoken.tokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  JwtTokenUtil jwtTokenUtil;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = null;
    String userName = null;

    String tokenHeader = request.getHeader("Authorization");

    if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
      token = tokenHeader.substring(7);
      try {
        userName = jwtTokenUtil.getUserNameFromToken(token);
      } catch(IllegalArgumentException e) {
        System.out.println("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("JWT Token has expired");
      }
    } else {
      System.out.println("Bearer String not found in token");

    }
    if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);
        if(jwtTokenUtil.validateJwtToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
              UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          usernamePasswordAuthenticationToken.setDetails(new
              WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
    }
    filterChain.doFilter(request, response);


  }
}

