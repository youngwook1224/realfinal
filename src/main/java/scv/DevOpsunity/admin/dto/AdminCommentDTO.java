package scv.DevOpsunity.admin.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("adminCommentDTO")
public class AdminCommentDTO {
    private int commentNo;
    private int freeArticleNo;
    private String id;
    private String commentContent;
    private String commentWriteDate;
}