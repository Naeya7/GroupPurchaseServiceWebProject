package common;

public class PageInfoDto {
	
	//Use Mapper
	private int member_id;			// 필요시에 추가
	private String param;			// 미 지정 파라미터
	private String param2;			// 미 지정 파라미터
	private String param3;			// 미 지정 파라미터	
	private int start;				// 현재페이지 시작 rownum
	private int end;				// 현재페이지 끝 rownum

	
	//페이지 셋팅값
	private int pageSize;			// 한 페이지당 글 개수
	private int pageBlock;			// 한 번에 출력할 페이지 개수
	private int cnt;				// 전체 글 개수
	private String pageNum;			// 현재 페이지
	private int currentPage;		// 계산용 현재페이지
	private int number;				// 출력용 글번호
	private int startPage;			// 시작페이지
	private int endPage;			// 끝페이지
	private int pageCount;			// 전체 페이지 개수
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}	
}
