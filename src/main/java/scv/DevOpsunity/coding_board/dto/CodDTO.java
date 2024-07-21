package scv.DevOpsunity.coding_board.dto;

import org.springframework.stereotype.Component;

@Component("CodDTO")
public class CodDTO {
    // mySql 컬럼 순서대로 해야 자동으로 편하게 쓰는 기능이 많다.
    private int articleNo;			// 글 번호
    private String title;			// 글 제목
    private String content;		// 작성 일자
    private String result;      // 정답
    private String id;				// 회원 아이디

    // 생성자
    public CodDTO() { //빈 생성자

    }
    public CodDTO(int articleNo , String title, String content, String result, String id) {
        this.articleNo = articleNo;
        this.title = title;
        this.content = content;
        this.result = result;
        this.id = id;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}