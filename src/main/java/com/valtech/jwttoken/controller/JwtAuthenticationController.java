package com.valtech.jwttoken.controller;

import com.valtech.jwttoken.tokenUtil.JwtRequest;
import com.valtech.jwttoken.tokenUtil.JwtResponse;
import com.valtech.jwttoken.tokenUtil.JwtUserDetailsService;
import com.valtech.jwttoken.tokenUtil.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class JwtAuthenticationController {

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @PostMapping(value = "/login")
  public ResponseEntity<?> createToken(@RequestBody JwtRequest request) throws Exception{
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
    } catch (DisabledException ex) {
      throw new Exception("USER disbled", ex);
    }catch (BadCredentialsException ex){
      throw new Exception("Invalid Exceptiopn");
    }
    final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUserName());
    final String jwtToken = jwtTokenUtil.generateJwtToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(jwtToken));

  }
}
