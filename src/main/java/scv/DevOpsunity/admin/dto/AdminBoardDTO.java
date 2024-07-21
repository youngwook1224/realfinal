package scv.DevOpsunity.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component("adminBoardDTO")
@NoArgsConstructor
public class AdminBoardDTO {
    private int freeArticleNo;
    private String freeTitle;
    private String freeContent;
    private String id;
    private String freeWriteDate;
    private String freeImageFileName;
}


