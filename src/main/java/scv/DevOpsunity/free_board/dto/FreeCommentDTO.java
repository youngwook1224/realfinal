package scv.DevOpsunity.free_board.dto;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component("FreeCommentDTO")
public class FreeCommentDTO {
    private int commentNo;
    private int freeArticleNo;
    private String id;
    private String commentContent;
    private Date commentWriteDate;

    // Constructor, getters, and setters
    public FreeCommentDTO() {}

    public FreeCommentDTO(int commentNo, int freeArticleNo, String id, String commentContent, Date commentWriteDate) {
        this.commentNo = commentNo;
        this.freeArticleNo = freeArticleNo;
        this.id = id;
        this.commentContent = commentContent;
        this.commentWriteDate = commentWriteDate;
    }


    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public int getFreeArticleNo() {
        return freeArticleNo;
    }

    public void setFreeArticleNo(int freeArticleNo) {
        this.freeArticleNo = freeArticleNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentWriteDate() {
        return commentWriteDate;
    }

    public void setCommentWriteDate(Date commentWriteDate) {
        this.commentWriteDate = commentWriteDate;
    }
}