package scv.DevOpsunity.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.admin.dao.AdminDAO;
import scv.DevOpsunity.admin.dto.AdminBoardDTO;
import scv.DevOpsunity.admin.dto.AdminCommentDTO;
import scv.DevOpsunity.admin.dto.AdminMemberDTO;

import java.util.List;


@Service("adminService")
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    public List<AdminBoardDTO> getAllBoards() {
        return adminDAO.getAllBoards();
    }

    public List<AdminMemberDTO> getAllMembers() {
        return adminDAO.getAllMembers();
    }

    public List<AdminCommentDTO> getAllComments() {
        return adminDAO.getAllComments();
    }

    public void deleteBoard(int freeArticleNo) {
        adminDAO.deleteBoard(freeArticleNo);
    }

    public void deleteMember(String id) {
        adminDAO.deleteMember(id);
    }

    public void deleteComment(int commentNo) {
        adminDAO.deleteComment(commentNo);
    }

    public long getTotalMembers() {
        return adminDAO.countMembers();
    }
}