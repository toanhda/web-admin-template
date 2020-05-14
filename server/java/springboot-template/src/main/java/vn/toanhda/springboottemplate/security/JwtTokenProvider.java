package vn.toanhda.springboottemplate.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/** Created by ToanHDA at  Feb 04, 2020 */
@Slf4j
//
@Component
public class JwtTokenProvider {

  private static final String AUTHORITIES_KEY = "XAUTHOR";

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  public String generateToken(Authentication authentication) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    final String authorities =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

    return Jwts.builder()
        .setSubject((String) authentication.getPrincipal())
        .claim(AUTHORITIES_KEY, authorities)
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public UsernamePasswordAuthenticationToken getUserInfoFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    Collection authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {

      System.out.println("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      System.out.println("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      System.out.println("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      System.out.println("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      System.out.println("JWT claims string is empty.");
    }
    return false;
  }
}
