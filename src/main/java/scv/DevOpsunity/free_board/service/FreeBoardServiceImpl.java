package scv.DevOpsunity.free_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import scv.DevOpsunity.free_board.dao.FreeBoardDAO;
import scv.DevOpsunity.free_board.dto.FreeArticleDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	private FreeBoardDAO freeBoardDAO;

	@Override
	public Map listArticles(Map<String, Integer> pagingMap) throws DataAccessException {
		Map articleMap = new HashMap();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		int count = (section-1)*100+(pageNum-1)*10;
		List<FreeArticleDTO> articlesList = freeBoardDAO.selectAllArticles(count);
		//페이징 처리
		int totArticles = freeBoardDAO.selectToArticles();
		articleMap.put("articlesList", articlesList);
		articleMap.put("totArticles", totArticles);
		return articleMap;
	}

	//한개의 이미지 추가
	@Override
	public int addArticle(FreeArticleDTO freeArticleDTO) throws DataAccessException {
		int freeArticleNo = freeBoardDAO.getNewArticleNo();
		freeArticleDTO.setFreeArticleNo(freeArticleNo);
		freeBoardDAO.insertNewArticle(freeArticleDTO);
		return freeArticleNo;
	}

	@Override
	public FreeArticleDTO viewArticle(int freeArticleNo) throws DataAccessException {
		FreeArticleDTO freeArticleDTO = freeBoardDAO.selectArticle(freeArticleNo);
		//조회수
		freeBoardDAO.updateReadCount(freeArticleNo);

		// 조회수가 증가된 글 정보를 다시 조회
		freeArticleDTO = freeBoardDAO.selectArticle(freeArticleNo);


		return freeArticleDTO;
	}
	

	@Override
	public void modArticle(FreeArticleDTO freeArticleDTO) throws DataAccessException {
		freeBoardDAO.updateArticle(freeArticleDTO);
		
	}

	@Override
	public void removeArticle(int freeArticleNo) throws DataAccessException {
		freeBoardDAO.deleteArticle(freeArticleNo);
		
	}

	public void selectSearch(Model model, String type, String keyword, int num) throws Exception {
		int pageLetter = 10;
		int allCount = freeBoardDAO.selectSearchCount(type, keyword);
		int repeat = (allCount + pageLetter - 1) / pageLetter;
		int end = num * pageLetter;
		int start = end - pageLetter + 1;

		model.addAttribute("repeat", repeat);
		model.addAttribute("boardList", freeBoardDAO.selectSearch(type, keyword, start - 1, pageLetter));
		model.addAttribute("num", num);
	}

	@Override
	public void boardList(Model model, int num) throws Exception {
		int pageLetter = 10;
		int allCount = freeBoardDAO.selectToArticles();
		int repeat = (allCount + pageLetter - 1) / pageLetter;
		int start = (num - 1) * pageLetter;

		List<FreeArticleDTO> boardList = freeBoardDAO.selectAllArticles(start);

		model.addAttribute("repeat", repeat);
		model.addAttribute("boardList", boardList);
		model.addAttribute("num", num);
	}


}
