package com.test.secured.service;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Provides an HTTP servlet request wrapper aware that this request may be
 * proxied by Netflix Zuul.
 */
class ZuulAwareRequestWrapper extends HttpServletRequestWrapper {

	private final HttpServletRequest request;

	private final UriComponents components;

	ZuulAwareRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
		this.components = ServletUriComponentsBuilder.fromRequest(request).build();
	}

	@Override
	public String getRequestURI() {
		return components.getPath();
	}

	@Override
	public StringBuffer getRequestURL() {
		return new StringBuffer(components.toUriString());
	}

	@Override
	public int getServerPort() {
		return components.getPort();
	}
}
