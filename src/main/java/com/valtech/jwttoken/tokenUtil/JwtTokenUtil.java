package com.valtech.jwttoken.tokenUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = 7008375124389347049L;
  public static final long TOKEN_VALIDITY = 10 * 60 * 60;
  @Value("${secret}")
  private String jwtSecret;

  public String generateJwtToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("place", "shimoga");
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
  }

  public Boolean validateJwtToken(String token, UserDetails userDetails) {
    String userName = getUserNameFromToken(token);
    Claims claims = Jwts
        .parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
    Boolean isExpired = claims.getExpiration().before(new Date());
    return userName.equals(userDetails.getUsername()) && !isExpired;

  }

  public String getUserNameFromToken(String token) {
    final Claims claims = Jwts
        .parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    System.out.println("this\n\n\nis\n\n"+claims.get("place"));

    return claims.getSubject();
  }
}
