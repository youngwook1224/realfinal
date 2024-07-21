package scv.DevOpsunity.coding_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import scv.DevOpsunity.coding_board.service.CodService;
import scv.DevOpsunity.coding_board.service.CodServiceImpl;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller("CodController")
public class CodControllerImpl implements CodController {

    @Autowired
    private CodService codBoardService;


    @Override
    @GetMapping("/codboard/codListArticles.do")
    public ModelAndView listArticles(
            @RequestParam(value = "section", required = false) String _section,
            @RequestParam(value = "pageNum", required = false) String _pageNum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        int section = Integer.parseInt((_section == null) ? "1" : _section);
        int pageNum = Integer.parseInt((_pageNum == null) ? "1" : _pageNum);
        Map<String, Integer> pagingMap = new HashMap<String, Integer>();
        pagingMap.put("section", section);
        pagingMap.put("pageNum", pageNum);
        Map articleMap = codBoardService.listArticles(pagingMap);
        articleMap.put("section", section);
        articleMap.put("pageNum", pageNum);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/codboard/codListArticles");
        mav.addObject("articleMap", articleMap);
        return mav;
    }


    //유저도 글쓸수 있을때
    @Override
    @GetMapping("/codboard/codArticleForm.do")
    public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/codboard/codArticleForm");
        return mav;
    }



    /*관리자만 글쓸때
    @Override
    @GetMapping("/codboard/codArticleForm.do")
    public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mav = new ModelAndView();
        if ("admin".equals(userId)) {
            mav.setViewName("/codboard/codArticleForm");
        } else {
            mav.setViewName("redirect:/member/loginForm.do");
            mav.addObject("result", "관리자만 글쓰기가 가능합니다.");
        }
        return mav;
    }
    */

    //글쓰기에 여러 개의 이미지 추가
    @Override
    @PostMapping("/codboard/codAddArticle.do")
    public ModelAndView addArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
            throws Exception {
        multipartRequest.setCharacterEncoding("utf-8");
        Map<String, Object> articleMap = new HashMap<String, Object>();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            articleMap.put(name, value);
        }
        HttpSession session = multipartRequest.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        String id = memberDTO.getId();
        articleMap.put("id", id);
        int articleNo = codBoardService.addArticle(articleMap);
        System.out.println(articleNo);
        ModelAndView mav = new ModelAndView("redirect:/codboard/codListArticles.do");
        return mav;
    }

    //여러 개의 이미지 상세 글보기
    @Override
    @GetMapping("/codboard/codViewArticle.do")
    public ModelAndView viewArticle(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map articleMap = codBoardService.viewArticle(articleNo);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/codboard/codViewArticle");
        mav.addObject("articleMap", articleMap);
        return mav;
    }

    //여러 개의 이미지 글 수정하기
    @Override
    @PostMapping("/codboard/codModArticle.do")
    public ModelAndView modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
            throws Exception {
        multipartRequest.setCharacterEncoding("utf-8");
        Map<String, Object> articleMap = new HashMap<String, Object>();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            articleMap.put(name, value);
        }
        HttpSession session = multipartRequest.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        String id = memberDTO.getId();
        articleMap.put("id", id);
        ModelAndView mav = new ModelAndView("redirect:/codboard/codListArticles.do");
        return mav;
    }

    @Override
    @PostMapping("/codboard/codRemoveArticle.do")
    public ModelAndView removeArticle(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        codBoardService.removeArticle(articleNo);
        ModelAndView mav = new ModelAndView("redirect:/codboard/codListArticles.do");
        return mav;
    }
}