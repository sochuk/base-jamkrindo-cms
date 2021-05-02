package com.iotanesia.jamkrindocms.security;

import com.iotanesia.jamkrindocms.dto.UsersDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Generate Token dan Validasi
@Component
public class JwtTokenUtil implements Serializable{

		private static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60 ;
		
		@Value("${jwt.secret}")
		private String secret;
		
		public String getUserNameFromToken(String token) {
			if(token != null && token.startsWith("Bearer ")) {
				return getClaimFromToken(token.substring(7, token.length()), Claims::getSubject);	
			}
			return getClaimFromToken(token, Claims::getSubject);
		}
		
		public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
			final Claims claims = getAllClaimsFromToken(token);
			return claimResolver.apply(claims);
		}
		
		private Claims getAllClaimsFromToken(String token) {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}
		
		public Date getExpirationDateFromToken(String token) {
			return getClaimFromToken(token, Claims::getExpiration);
		}
		
		private Boolean isTokenExpired(String token) {
			final Date expiration = getExpirationDateFromToken(token);
			return expiration.before(new Date());
		}
		
		public String generateToken(UserDetails userDetails, UsersDto usersDto) {
			Map<String , Object> claims = new HashMap<>();
			usersDto.setPasswordUser(null);
			claims.put("users" , usersDto);
			return doGenerateToken(claims, userDetails.getUsername());
		}
		
		private String doGenerateToken (Map<String , Object> claims , String subject) {
			return Jwts.builder()
					.setClaims(claims)
					.setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
					.signWith(SignatureAlgorithm.HS512, secret).compact();
		}
		
		public Boolean validateToken(String token, UserDetails userDetails) {
			final String username = getUserNameFromToken(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}
		
}
