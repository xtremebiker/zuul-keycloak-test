package com.test.secured.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP servlet filter that wraps the current request in an {@link ZuulAwareRequestWrapper}
 * to appropriately expose request parameters populated by Zuul.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ZuulAwareFilter extends OncePerRequestFilter implements Filter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		chain.doFilter(new ZuulAwareRequestWrapper(request), response);
	}
}
