package scv.DevOpsunity.free_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.free_board.dao.FreeCommentDAO;
import scv.DevOpsunity.free_board.dto.FreeCommentDTO;

import java.util.List;

@Service("freeCommentService")
public class FreeCommentServiceImpl implements FreeCommentService  {

    @Autowired
    private FreeCommentDAO freeCommentDAO;

    @Override
    public List<FreeCommentDTO> listComments(int freeArticleNo) throws Exception {
        return freeCommentDAO.selectAllComments(freeArticleNo);
    }

    @Override
    public void addComment(FreeCommentDTO freeCommentDTO) throws Exception {
        freeCommentDAO.insertNewComment(freeCommentDTO);
    }

    @Override
    public void modifyComment(FreeCommentDTO freeCommentDTO) throws Exception {
        freeCommentDAO.updateComment(freeCommentDTO);
    }

    @Override
    public void removeComment(int commentNo) throws Exception {
        freeCommentDAO.deleteComment(commentNo);
    }
}