package scv.DevOpsunity.member.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import scv.DevOpsunity.member.dao.MemberDAO;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
   
   @Autowired
   private MemberDAO memberDAO;

   @Autowired
   private JavaMailSender sender;

    @Autowired
    private MailService mailService;

    @Override
   public List listMembers() throws DataAccessException {
      List membersList = memberDAO.selectAllMembersList();
      return membersList;
   }

    @Override
    public void addMember(MemberDTO memberDTO) throws DataAccessException {

            memberDAO.insertMember(memberDTO);

    }

    @Override
    public MemberDTO findMember(String id) throws DataAccessException {
        MemberDTO memberDTO = memberDAO.selectMemberById(id);
        return memberDTO;
    }

    @Override
    public void updateMember(MemberDTO memberDTO) throws DataAccessException {
        memberDAO.updateMember(memberDTO);

    }

    @Override
    public void delMember(String id) throws DataAccessException {
        memberDAO.delMember(id);

    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
        return memberDAO.loginCheck(memberDTO);
    }

    @Override
    public int idCheck(String id) {
        int cnt = memberDAO.idCheck(id);
        return cnt;
    }



    @Override
    public String findIdByEmail(String email) throws DataAccessException {
        return memberDAO.findIdByEmail(email);
    }


/*
    @Override
    public boolean sendPasswordResetEmail(String email) throws DataAccessException {
        MemberDTO member = memberDAO.findMemberByEmail(email);
        if (member != null) {
            Map<String, Object> sendResult = mailService.send(email, "비밀번호 재설정",
                    "아래의 인증 코드를 입력하여 비밀번호를 재설정하세요:");
            return (boolean) sendResult.get("success");
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String code, String newPassword) throws DataAccessException, Exception {
        if (mailService.verifyCode(email, code)) {
            MemberDTO member = memberDAO.findMemberByEmail(email);
            if (member != null) {
                memberDAO.updatePassword(member.getId(), newPassword);
                return true;
            }
        }
        return false;
    }

*/

}






