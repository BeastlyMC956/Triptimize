package com.beastlymc.triptimize.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * A service for handling JWT authentication tokens.
 */
@Service
public class JwtService {

    /**
     * The signing key for the JWT tokens.
     */
    @Value("${SIGNING_KEY}")
    private String key;

    /**
     * Extracts the username from a JWT token.
     *
     * @param token the JWT token to extract the username from
     * @return the username contained in the JWT token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generates a http only cookie with the given token.
     *
     * @param token the token to generate the cookie with
     * @return the generated cookie
     */
    public Cookie generateCookie(String token) {
        final int ONE_WEEK = 60 * 60 * 24 * 7;
        Cookie jwtCookie = new Cookie("token", token);

        jwtCookie.setMaxAge(ONE_WEEK);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");

        return jwtCookie;
    }

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails the user details to generate the token for
     * @return the generated JWT token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with additional claims for the given user details.
     *
     * @param extraClaims additional claims to include in the JWT token
     * @param userDetails the user details to generate the token for
     * @return the generated JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, @NotNull UserDetails userDetails) {
        final Date aWeekFromNow = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(aWeekFromNow)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Checks if a JWT token is valid for the given user details.
     *
     * @param token       the JWT token to check
     * @param userDetails the user details to check the token against
     * @return true if the token is valid for the user details, false otherwise
     */
    public boolean isTokenValid(String token, @NotNull UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if a JWT token has expired.
     *
     * @param token the JWT token to check
     * @return true if the token has expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration from the token
     *
     * @param token the JWT token to extract from
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a claim from a JWT token using a claims resolver function.
     *
     * @param token          the JWT token to extract the claim from
     * @param claimsResolver a function that resolves the desired claim from the token's claims
     * @param <T>            the type of the claim to extract
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token the JWT token to extract the claims from
     * @return a Claims object containing all claims from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * Returns the signing key used for JWT tokens.
     *
     * <p>The key is read from the application configuration file.</p>
     *
     * @return the signing key
     * @throws IllegalStateException if the key is not found in the configuration file
     */
    private @NotNull Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
