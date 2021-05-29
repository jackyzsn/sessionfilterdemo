package com.jszsoft.myweb.restback.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SessionFilter implements Filter {

	@Value("${session-check-exclusion}")
	private String[] excludeURL;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession(false);

		String path = req.getRequestURI();

		boolean found = false;

		for (int i = 0; i < excludeURL.length; i++) {
			if (excludeURL[i].equals(path)) {
				found = true;
				break;
			}
		}

		if (found) {
			chain.doFilter(request, response); // Just continue chain.
		} else {
			if (hasValidSession(session)) {
				chain.doFilter(request, response);
			} else {
				// redirect
				((HttpServletResponse) response).sendRedirect("/doLogin");
			}
		}

	}

	private boolean hasValidSession(HttpSession session) {
		if (session == null) {
			return false;
		}

		String loggedIn = (String) session.getAttribute("loggedIn");

		return (loggedIn != null && loggedIn.equals("Yes"));
	}

}
