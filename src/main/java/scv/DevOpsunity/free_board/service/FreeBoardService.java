package scv.DevOpsunity.free_board.service;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import scv.DevOpsunity.free_board.dto.FreeArticleDTO;

import java.util.Map;

public interface FreeBoardService {
	
	public Map listArticles(Map<String, Integer> pagingMap) throws DataAccessException;
	
	//한개의 이미지 추가
	 public int addArticle(FreeArticleDTO freeArticleDTO) throws DataAccessException;
	
	public FreeArticleDTO viewArticle(int feeArticleNo) throws DataAccessException;
	
	//한개의 이미지 글 수정
	public void modArticle(FreeArticleDTO freeArticleDTO) throws DataAccessException;
	
	public void removeArticle(int articleNo) throws DataAccessException;

	public void selectSearch(Model model, String type, String keyword, int num) throws Exception;

	public void boardList(Model model, int num) throws Exception;
	
}