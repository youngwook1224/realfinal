package scv.DevOpsunity.coding_board.service;

import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface CodService {

    public Map listArticles(Map<String, Integer> pagingMap) throws DataAccessException;

    public int addArticle(Map articleMap) throws DataAccessException;

    public Map viewArticle(int articleNo) throws DataAccessException;

    public void modArticle(Map articleMap) throws DataAccessException;

    public void removeArticle(int articleNo) throws DataAccessException;

}

