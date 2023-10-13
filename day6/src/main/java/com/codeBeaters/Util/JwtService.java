package com.codeBeaters.Util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

	
	private static final String SECRET_KEY = "8909bf08e4b3dc723ec3b04d3eb031598005b0c2ca32a0e72a85e88dbbf515ac";
	
		
	public String extractUsername(String token) {
		
		return extractClaims(token, Claims::getSubject);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		
		Claims claim = extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	
	
	public String generateToken(UserDetails userDetaisl) {
		
		return generateToken(new HashMap<>(), userDetaisl);
	}
	
	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSigninKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Claims extractAllClaims(String token) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigninKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	
	private Key getSigninKey() {

		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		
		return (username.equals(userDetails.getUsername())) && !iSTokenExpired(token);
	}

	
	
	private boolean iSTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}
	
	

	private Date extractExpiration(String token) {
		
		return extractClaims(token, Claims::getExpiration);
	}
}
