package common;

public class PageInfoProcess {
	
	//셋팅 변수
	private int pageSize = 5;			// 한 페이지당 글 개수
	private int pageBlock = 5;			// 한 번에 출력할 페이지 개수
	
	//싱글톤 패턴
	public static PageInfoProcess instance = new PageInfoProcess();
	
	//세션 가져오기
	public static PageInfoProcess getInstance() {
		return instance;
	}
	
	//페이지 처리
	public PageInfoDto process(int cnt, String pageNum){
		
		//내부 처리 변수
		int currentPage = 0;		
		int start = 0;				
		int end = 0;				
		int number = 0;				
		int startPage = 0;			
		int endPage = 0;			
		int pageCount = 0;			
		
		//첫 페이지인 경우
		if(pageNum == null) {
			pageNum = "1";
		}	
		
		//현재 페이지
		currentPage = Integer.parseInt(pageNum);
		
		//시작 페이지
		start = (currentPage-1) * pageSize + 1;
		
		//끝 번호 처리
		end = start + pageSize -1;	
		
		//끝 지점이 개수보다 작으면
		if(end > cnt) {
			//개수를 끝으로 지정
			end = start + cnt;
		}
		
		//게시글 번호 처리
		number = cnt - (currentPage - 1) * pageSize;
		
		//페이지 개수 처리
		pageCount = cnt / pageSize + (cnt % pageSize > 0 ? 1 : 0);
		
		//시작 페이지 처리
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		
		//시작 페이지 예외처리
		if(currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}
		
		//끝페이지 처리
		endPage = startPage + pageBlock - 1;
		
		//끝페이지 예외처리 
		if(endPage>pageCount) {
			endPage = pageCount;
		}
			
		//결과 입력
		PageInfoDto info = new PageInfoDto();	
		info.setCnt(cnt);
		info.setPageNum(pageNum);
		info.setPageSize(pageSize);
		info.setPageBlock(pageBlock);
		info.setCurrentPage(currentPage);
		info.setStart(start);
		info.setEnd(end);
		info.setNumber(number);
		info.setStartPage(startPage);
		info.setEndPage(endPage);
		info.setPageCount(pageCount);
		
		//페이지 정보 리턴
		return info;
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
	
}
