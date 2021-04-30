package com.guithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guithub.service.board.general.PostService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	PostService service;
	
	//메인페이지
	@RequestMapping("index")
	public String index() {
		
		return "main.index";
	}
	
}
