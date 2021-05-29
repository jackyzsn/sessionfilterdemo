package com.jszsoft.myweb.restback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jszsoft.myweb.restback.filter.SessionFilter;

@SpringBootApplication
public class RestbackApplication {

	@Bean
	public FilterRegistrationBean<SessionFilter> loggingFilter() {
		FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new SessionFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestbackApplication.class, args);
	}

}
