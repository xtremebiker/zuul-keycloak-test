package com.test.secured.service;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Zuul HTTP <code>x-forwarded-prefix</code> header aware authentication entry point.
 */
public class ZuulAwareKeycloakAuthenticationEntryPoint extends KeycloakAuthenticationEntryPoint {
	private final Logger logger = LoggerFactory.getLogger(ZuulAwareKeycloakAuthenticationEntryPoint.class);

	protected void commenceLoginRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String prefix = request.getHeader("x-forwarded-prefix");
		UriComponents components =
				ServletUriComponentsBuilder.fromRequest(request)
						.replacePath((prefix != null ? prefix : "") + "/sso/login").build();

		logger.info("Redirecting to login URI: {}", components.toUriString());
		response.sendRedirect(components.toUriString());
	}
}
