package scv.DevOpsunity.free_board.dto;


import org.springframework.stereotype.Component;

@Component("SearchForm")
public class SearchForm {
    private String type;
    private String keyword;

    // 기본 생성자
    public SearchForm() {
    }

    // getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}