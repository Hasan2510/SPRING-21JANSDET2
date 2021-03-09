package com.example.duckdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.duckdemo.dto.DuckDTO;

@Controller
public class BaseController {

	@GetMapping("/test")
	@ResponseBody
	public DuckDTO returnSomething() {
		return new DuckDTO(23, "Fred", "Red", "Beach");
	}
	
}
