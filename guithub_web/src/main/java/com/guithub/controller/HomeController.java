package com.guithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guithub.domain.PostVO;
import com.guithub.service.PostService;

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
