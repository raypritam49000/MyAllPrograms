package com.jersey.rest.api.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jersey.rest.api.dto.UserDto;
import com.jersey.rest.api.utils.JwtUtility;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTTokenNeededFilter.class);
	private static final String BEARER_PREFIX = "Bearer";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			sendUnauthorizedResponse(requestContext, "Authorization header must be provided");
			return;
		}
		// Extract the token from the HTTP Authorization header
		String token = extractToken(authorizationHeader);

		System.out.println(token);

		try {
			// Validate the token and decode user information
			UserDto userDto = JwtUtility.decodeToken(token);
			
			System.out.println("JWT User Info:"+ userDto);

			// Set the security context
			setSecurityContext(requestContext, userDto);

		} catch (Exception e) {
			// Handle specific exception for token validation failure
			logger.error("JWT validation failed: {}", e.getMessage());
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private String extractToken(String authorizationHeader) {
		return authorizationHeader.substring(BEARER_PREFIX.length()).trim();
	}

	private void setSecurityContext(ContainerRequestContext requestContext, UserDto userDto) {
		final SecurityContext securityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {
			@Override
			public Principal getUserPrincipal() {
				return () -> userDto.getUsername();
			}

			@Override
			public boolean isUserInRole(String role) {
				// Implement role-based access control if needed
				return false;
			}

			@Override
			public boolean isSecure() {
				return securityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme() {
				return BEARER_PREFIX;
			}
		});
	}

	private void sendUnauthorizedResponse(ContainerRequestContext requestContext, String message) {
		Response unauthorizedResponse = Response.status(401).status(Response.Status.UNAUTHORIZED)
				.entity(Map.of("message", message, "statusCode", 401, "status", "Unauthorized User", "path",
						requestContext.getUriInfo().getBaseUri().getPath() + "" + requestContext.getUriInfo().getPath(),
						"date", new Date()))
				.type(MediaType.APPLICATION_JSON).build();
		requestContext.abortWith(unauthorizedResponse);
	}

}