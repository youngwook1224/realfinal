package scv.DevOpsunity.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scv.DevOpsunity.admin.dto.AdminBoardDTO;
import scv.DevOpsunity.admin.dto.AdminCommentDTO;
import scv.DevOpsunity.admin.dto.AdminMemberDTO;

import java.util.List;

@Mapper
@Repository("adminDAO")
public interface AdminDAO {
    List<AdminBoardDTO> getAllBoards();
    List<AdminMemberDTO> getAllMembers();
    List<AdminCommentDTO> getAllComments();
    void deleteBoard(int freeArticleNo);
    void deleteMember(String id);
    void deleteComment(int commentNo);

    @Select("SELECT COUNT(*) FROM member_tbl")
    long countMembers();
}