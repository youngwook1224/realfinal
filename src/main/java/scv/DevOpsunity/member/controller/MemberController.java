package scv.DevOpsunity.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import scv.DevOpsunity.member.dto.MemberDTO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request,
									HttpServletResponse response) throws Exception;

	public ModelAndView addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView memberForm(HttpServletRequest request,
								   HttpServletResponse response) throws Exception;

	public ModelAndView modMemberForm(@RequestParam("id") String id,
									  HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO,
									 HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView delMember(@RequestParam("id") String id,
								  HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView loginForm(@ModelAttribute("member") MemberDTO member,
								  @RequestParam(value = "action", required = false) String action,
								  @RequestParam(value = "result", required = false) String result,
								  HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView login(@ModelAttribute("member") MemberDTO member,
							  RedirectAttributes rAttr,
							  HttpServletRequest request, HttpServletResponse response) throws Exception;


	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;


}

