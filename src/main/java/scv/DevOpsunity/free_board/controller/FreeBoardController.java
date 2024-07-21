package scv.DevOpsunity.free_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface FreeBoardController {

	public ModelAndView listArticles(

			@RequestParam("section") String _section,
			@RequestParam("pageNum") String _pageNum,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public ModelAndView articleForm(HttpServletRequest request , HttpServletResponse response) throws Exception;
	
	public ModelAndView addArticle(MultipartHttpServletRequest multipartRequest , HttpServletResponse response) throws Exception;	// 이미지 등 파일 업로드는은 멀티 파트 처리가 되야 함
	
	public ModelAndView viewArticle(@RequestParam("freeArticleNo") int freeArticleNo, HttpServletRequest request , HttpServletResponse response) throws Exception;
	
	public ModelAndView modArticle(MultipartHttpServletRequest multipartRequest , HttpServletResponse response) throws Exception;
	
	public ModelAndView removeArticle(@RequestParam("freeArticleNo") int freeArticleNo, HttpServletRequest request , HttpServletResponse response) throws Exception;

	public String review(Model model,
						 @RequestParam(value="type", required=false) String type,
						 @RequestParam(value="keyword", required=false) String keyword,
						 @RequestParam(required = false, defaultValue = "1") int num) throws Exception;
}
