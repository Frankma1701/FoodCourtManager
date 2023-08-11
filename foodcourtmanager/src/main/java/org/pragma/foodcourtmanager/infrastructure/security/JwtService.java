package org.pragma.foodcourtmanager.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService{

    //@Value("${jwt.secret.key}")
    private static final String SECRET_KEY= "CestL9uDprmHHcnV7VNyFjnUGkAZTqQVYBGdBETbmqwJmt2xgSgy8rG2p65m95F6EG5CQ9TBjCBScLsQJY3XQ2p5yGQEsxGZP7gdLZaKUYTGFgjZEMB7gX3tQaSHQY4GmJCjzSd5dCbdCrNhryP6WvDT5mHrsE2F4f4nHxSPef3jY7LcyT72dqPgxhy5ZEeJEPGNSykQpjCyKNhAS7Vu9mRT2XCshch3FwSjBDwzMK8P2s2UDanLCHarcERhAJ4s" ;


    public String extractUsername (String token){
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String , Object> extraClaims,
        UserDetails userDetails){
        List<String > roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
        extraClaims.put("roles", roles);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token , @NotNull  UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public UserDetails extractUserDetailsFromToken(String jwt) {
        final Claims claims = extractClaims(jwt);
        String username = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        // Convert role strings to SimpleGrantedAuthority objects
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        // Create UserDetails object with username, roles, and empty password (as it's not needed)
        return new User(username, "", authorities);
    }
}
