package com.gura.spring05.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.member.dao.MemberDao;
import com.gura.spring05.member.dto.MemberDto;

@Controller
public class MemberController {
	//의존 객체 주입받기(DI)
	@Autowired
	private MemberDao dao;
	
	//회원목록 보기 요청(/member/list.do)을 처리 할 컨트롤러의 메소드
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//회원목록을 얻어오려면?
		List<MemberDto> list=dao.getList();
		
		mView.addObject("list", list); //("key값", type)	
		mView.setViewName("member/list"); //forward이동-> /WEB_INF/views/member/list.jsp
		return mView;
	}
	@RequestMapping("/member/delete")
	//회원정보 삭제 요청 처리
	public String delete(@RequestParam int num) {
		//MemberDao 객체를 이용해서 회원정보 삭제
		dao.delete(num);
		//리다이렉트 응답(자동으로 재요청)
		return "redirect:/member/list.do";
	}
}
