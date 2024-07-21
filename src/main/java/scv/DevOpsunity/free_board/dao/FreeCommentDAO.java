package scv.DevOpsunity.free_board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.free_board.dto.FreeCommentDTO;

import java.util.List;

@Mapper
@Repository("freeCommentDAO")
public interface FreeCommentDAO {
    public List<FreeCommentDTO> selectAllComments(int freeArticleNo) throws DataAccessException;
    public void insertNewComment(FreeCommentDTO freeCommentDTO) throws DataAccessException;
    public void updateComment(FreeCommentDTO freeCommentDTO) throws DataAccessException;
    public void deleteComment(int commentNo) throws DataAccessException;
    public int getCommentCount(int freeArticleNo) throws DataAccessException;
}