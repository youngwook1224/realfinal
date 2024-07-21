package scv.DevOpsunity.admin.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("adminMemberDTO")
public class AdminMemberDTO {
    private String id;
    private String pw;
    private String name;
    private int phoneNum;
    private String email;
    private String joinDate;
}
