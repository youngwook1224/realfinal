package scv.DevOpsunity.coding_board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.coding_board.dto.CodDTO;

import java.util.List;
import java.util.Map;


@Mapper
@Repository("CodDAO")
public interface CodDAO {

    public List selectAllArticles(@Param("count") int count) throws DataAccessException;

    public int selectToArticles() throws DataAccessException;

    public int getNewArticleNo() throws DataAccessException;

    public void insertNewArticle(Map articleMap) throws DataAccessException;

    public CodDTO selectArticle(int articleNo) throws DataAccessException;

    public void updateArticle(Map articleMap) throws DataAccessException;

    public void deleteArticle(int articleNo) throws DataAccessException;

}