package com.jersey.rest.api.utils;

import java.util.Date;

import com.jersey.rest.api.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtility {

	private static final String SECRET_KEY = "secretKey";

	public static String generateToken(UserDto userDto) {
		System.out.println("user" + userDto);
		String jwtToken = Jwts.builder().claim("id", userDto.getId()).claim("username", userDto.getUsername())
				.claim("email", userDto.getEmail()).claim("city", userDto.getCity()).setSubject(userDto.getUsername())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		return jwtToken;
	}

	public static UserDto decodeToken(String jwtToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
			String id = (String) claims.get("id");
			System.out.println("@@@ Id ::: " + id);
			String email = (String) claims.get("email");
			String username = (String) claims.get("username");
			String city = (String) claims.get("city");
			return new UserDto(id, username, email, null, city);
		} catch (SignatureException e) {
			System.err.println("Invalid JWT signature");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isTokenValid(String jwtToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
			if (claims.getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (SignatureException e) {
			System.err.println("Invalid JWT signature");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
