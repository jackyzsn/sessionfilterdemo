package com.jszsoft.myweb.restback.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

	@GetMapping(path = "/login", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request) {

		request.getSession().setAttribute("loggedIn", "Yes");

		return "You are logged in now..";
	}

	@GetMapping(path = "/logout", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logout(HttpServletRequest request) {

		request.getSession().invalidate();

		return "You are logged out..";
	}

	@GetMapping(path = "/home", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String home(HttpServletRequest request) {

		return "Home Page..";
	}

	@GetMapping(path = "/doLogin", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String reqLogin(HttpServletRequest request) {

		return "Please login..";
	}

}
