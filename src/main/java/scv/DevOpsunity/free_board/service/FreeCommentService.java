package scv.DevOpsunity.free_board.service;

import scv.DevOpsunity.free_board.dto.FreeCommentDTO;

import java.util.List;

public interface FreeCommentService {

    public List<FreeCommentDTO> listComments(int freeArticleNo) throws Exception;

    public void addComment(FreeCommentDTO freeCommentDTO) throws Exception;

    public void modifyComment(FreeCommentDTO freeCommentDTO) throws Exception;

    public void removeComment(int commentNo) throws Exception;
}
