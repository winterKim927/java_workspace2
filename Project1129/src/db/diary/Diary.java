package db.diary;

//이 클래스는 테이블의 인스턴스 1개를 매칭할 수 있는 용도로 정의
//오직 데이터만을 담기 위한 용도의 클래스 == DTO(Data Transfer Object)
public class Diary {
	private int diary_idx;
	private int yy;
	private int mm;
	private int dd;
	private String content;
	private String icon;
	
	public int getDiary_idx() {
		return diary_idx;
	}
	public void setDiary_idx(int diary_idx) {
		this.diary_idx = diary_idx;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
