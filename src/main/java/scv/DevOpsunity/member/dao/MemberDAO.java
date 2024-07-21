package scv.DevOpsunity.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.List;

@Mapper   //Mapper 어노테이션 : 추상 메소드와 이름이 같은 마이바티스의 id 태그를 불러들임 
@Repository("memberDAO")
public interface MemberDAO {
   // 마이바티스(member.xml)에서 selectAllMembersList 이름을 가진 태그를 찾아 리턴값 받은 뒤 List에 값을 넣음
   @Transactional
   public List selectAllMembersList () throws DataAccessException;
   @Transactional
   public void insertMember(MemberDTO memberDTO) throws DataAccessException;
   public MemberDTO selectMemberById(String id) throws DataAccessException;
   @Transactional
   public void updateMember(MemberDTO memberDTO) throws DataAccessException;
   @Transactional
   public void delMember(String id) throws DataAccessException;
   @Transactional
   public MemberDTO loginCheck(MemberDTO memberDTO) throws DataAccessException;
   @Transactional
   public int idCheck (String id) throws DataAccessException;
   @Transactional
   public String findIdByEmail(String email)throws DataAccessException;
   /*
   @Transactional
   public MemberDTO findMemberByEmail(String email) throws DataAccessException;
   @Transactional
   public void updatePassword(String id, String newPassword) throws DataAccessException;
    */
}
