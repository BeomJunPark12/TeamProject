package com.gdj59.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main(@SessionAttribute(name = "isLogOn", required = false) Boolean myCheck, Model model) {
		if (myCheck == null) {
			myCheck = false;
		}
		model.addAttribute("myCheck", myCheck);
		return "main";
	}

}
