package scv.DevOpsunity.free_board.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("freeArticleDTO")
public class FreeArticleDTO {
	private int freeArticleNo;       // 글 번호
	private String freeTitle;        // 글 제목
	private String freeContent;       // 작성 일자
	private Date freeWriteDate;
	private String id;          // 회원 아이디
	private String freeImageFileName;

	// 생성자
	public FreeArticleDTO() { //빈 생성자

	}
	public FreeArticleDTO(int freeArticleNo, String freeTitle, String freeContent, String freeImageFileName, Date freeWriteDate, String id) {
		this.freeArticleNo = freeArticleNo;
		this.freeTitle = freeTitle;
		this.freeContent = freeContent;
		this.id = id;
		this.freeWriteDate = freeWriteDate;
		this.freeImageFileName = freeImageFileName;
	}

	// getter, setter 메서드
	public int getFreeArticleNo() {
		return freeArticleNo;
	}

	public void setFreeArticleNo(int freeArticleNo) {
		this.freeArticleNo = freeArticleNo;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}

	public Date getFreeWriteDate() {
		return freeWriteDate;
	}

	public void setFreeWriteDate(Date freeWriteDate) {
		this.freeWriteDate = freeWriteDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreeImageFileName() {
		return freeImageFileName;
	}

	public void setFreeImageFileName(String freeImageFileName) {
		this.freeImageFileName = freeImageFileName;
	}
}