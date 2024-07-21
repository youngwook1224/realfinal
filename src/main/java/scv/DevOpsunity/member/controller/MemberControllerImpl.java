package scv.DevOpsunity.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import scv.DevOpsunity.member.dto.MemberDTO;
import scv.DevOpsunity.member.service.MemberServiceImpl;

import java.util.List;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired //자동주입 어노테이션
	private MemberServiceImpl memberService;
	@Autowired
	private MemberDTO memberDTO;

	@GetMapping("/member/listMembers.do")
	public ModelAndView listMembers(HttpServletRequest request , HttpServletResponse response) throws Exception {
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/listMembers");
		mav.addObject("membersList",membersList);
		return mav;
	}

	@Override
	@PostMapping("/member/addMember.do")
	public ModelAndView addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.addMember(memberDTO);
		ModelAndView mav=new ModelAndView("redirect:/main.do");
		return mav;
	}


	@Override
	@GetMapping("/member/memberForm.do")
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberForm");
		return mav;
	}

	 @Override
	   @GetMapping("/member/modMemberForm.do")
	   public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	        memberDTO=memberService.findMember(id);
	        ModelAndView mav=new ModelAndView("/member/modMemberForm");
	        mav.addObject("member",memberDTO);
	      return mav;
	   }

	   @Override
	   @PostMapping("/member/updateMember.do")
	   public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	         request.setCharacterEncoding("utf-8");
	         memberService.updateMember(memberDTO);
	         ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
	      return mav;
	   }

	   @Override
	   @GetMapping("/member/delMember.do")
	   public ModelAndView delMember(String id, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	          memberService.delMember(id);
	          ModelAndView mav=new ModelAndView("redirect:/member/listMembers.do");
	      return mav;
	   }
	   
	   @Override
	   @GetMapping("/member/loginForm.do")
	   public ModelAndView loginForm(@ModelAttribute("member") MemberDTO member,
				@RequestParam(value = "action" , required = false) String action,
				@RequestParam(value = "result", required = false) String result,
			   HttpServletRequest request, HttpServletResponse response) throws Exception{
		   HttpSession session = request.getSession();	//  getSession() : 세션이 있다면 가져오고 없다면 생성
		   session.setAttribute("action", action);
		   // Referer 헤더 저장
		   String referer = request.getHeader("Referer");
		   if (referer != null && !referer.contains("/member/loginForm.do")) {
			   session.setAttribute("prevPage", referer);
		   }

		   ModelAndView mav = new ModelAndView();
		   mav.addObject("result", result);
		   mav.setViewName("/member/loginForm");
		   return mav;
	   }

	@Override
	@PostMapping("/member/login.do")
	public ModelAndView login(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		memberDTO = memberService.login(member);
		ModelAndView mav = new ModelAndView();
		if(memberDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO);
			session.setAttribute("isLogOn", true);
			session.setAttribute("userId", memberDTO.getId());
			String action = (String)session.getAttribute("action");
			// 추가된 부분: 이전 페이지 URL을 세션에서 가져옴
			String prevPage = (String)session.getAttribute("prevPage");

			if(action != null) {
				mav.setViewName("redirect:" + action);
				// 추가된 부분: 이전 페이지 URL이 존재하면 그 페이지로 리다이렉트하고 세션에서 제거
			} else if (prevPage != null) {
				mav.setViewName("redirect:" + prevPage);
				session.removeAttribute("prevPage");
			} else {
				mav.setViewName("redirect:/main.do");
			}
		} else {
			rAttr.addAttribute("result","아이디나 비밀번호가 틀립니다. 다시 로그인해 주세요");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}


	@Override
	@GetMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}


	//아이디 중복체크
	@PostMapping("/member/idCheck.do")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id ,HttpServletRequest request, HttpServletResponse response)throws Exception {
		int cnt = memberService.idCheck(id);
		return cnt;
	}

	
	
	
	
	// 아이디,비밀번호 찾기



		//아이디
		@GetMapping("/member/findIdForm.do")
		public String showFindIdForm() {
			return "/member/findIdForm";
		}
	
		@PostMapping("/member/findId.do")
		public String findId(@RequestParam String email, Model model) {
			String id = memberService.findIdByEmail(email);
			if (id != null) {
				model.addAttribute("foundId", id);
			} else {
				model.addAttribute("error", "해당 이메일로 등록된 아이디가 없습니다.");
			}
			return "/member/findIdResult";
		}
	


		/*비밀번호

	@GetMapping("/member/resetPasswordForm.do")
	public String showResetPasswordForm() {
		return "/member/resetPasswordForm";
	}

	@PostMapping("/member/sendResetPasswordEmail.do")
	@ResponseBody
	public String sendResetPasswordEmail(@RequestParam String email) {
		boolean success = memberService.sendPasswordResetEmail(email);
		return success ? "success" : "fail";
	}

	@PostMapping("/member/resetPassword.do")
	public String resetPassword(@RequestParam String email, @RequestParam String code,
								@RequestParam String newPassword, Model model) {
		boolean success = memberService.resetPassword(email, code, newPassword);
		if (success) {
			model.addAttribute("message", "비밀번호가 성공적으로 재설정되었습니다.");
		} else {
			model.addAttribute("error", "비밀번호 재설정에 실패했습니다. 이메일과 인증 코드를 확인해주세요.");
		}
		return "/member/resetPasswordResult";
	}

	*/




}
