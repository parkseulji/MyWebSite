package com.gura.spring05.users.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.type.MapType;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	//회원가입 폼 요청 처리
	@RequestMapping("/users/signup_form")
	public String signup_form() {
		return "users/signup_form";
	}
	/*
	 * [JSON 문자열 응답하는 방법]
	 * 1. pom.xml에 jackson-databind dependency명시
	 * 2. controller의 메소드에 @ResponseBody어노테이션 붙이기
	 * 3. List, Map, Dto중에 하나를 리턴한다.
	 */
	@ResponseBody
	@RequestMapping("/users/checkid")
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map=service.isExistId(inputId);
		return map;
	}
	@RequestMapping(value = "/users/signup", method = RequestMethod.POST) //post방식일때만 요청하려면 명시를 해 두는것이 좋다.
	public ModelAndView signup(@ModelAttribute("dto") UsersDto dto,
			ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("users/insert"); //어디로 forward이동 할 지
		return mView;
	}
}
