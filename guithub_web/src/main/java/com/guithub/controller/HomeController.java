package com.guithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guithub.domain.PostVO;
import com.guithub.service.PostService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	PostService service;
	
	@RequestMapping("bbs")
	public String bbs(Model model) throws Exception {
		List<PostVO> list = service.getList();
		
		model.addAttribute("list", list);
		
		return "board.bbs";
	}
	
	@RequestMapping("index")
	public String index() {
		System.out.println("test");
		return "main.index";
	}
	
	@RequestMapping("index2")
	public void index2() {
		
	
	}
	
	@RequestMapping("join")
	public void join() {
		
	}
	
	@RequestMapping("login")
	public void login() {
		
	}
	
	@RequestMapping("update")
	public void update() {
		
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam("id") int id, Model model) {
		PostVO detail = service.getDetail(id);
		
		model.addAttribute("detail", detail);
		return"board.detail";
	}
	
	@RequestMapping("write")
	public String write() {
		
		return "board.write";
	}
	

	
}
