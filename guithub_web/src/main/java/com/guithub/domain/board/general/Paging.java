package com.guithub.domain.board.general;

public class Paging {
	
	public Paging(int page, int total_cnt) {
		this.page = page;
		this.total_cnt = total_cnt;
		
		dataCalc();
	}

	//현재 페이지
	private int page;
	
	//게시물 총 개수
	private int total_cnt;
	
	//한 페이지당 나타낼 게시물 개수
	private int post_cnt = 15;
	
	//페이징 목록에 나타낼 페이지 개수
	private int page_cnt= 10;
	
	//페이지 끝, 시작 번호 
	private int endPageNum;
	private int startPageNum;
	
	//다음, 이전 버튼 표시 여부
	private boolean prev;
	private boolean next;
	
	
	public int getPage() {
		return page;
	}

	public int getTotal_cnt() {
		return total_cnt;
	}

	public int getPost_cnt() {
		return post_cnt;
	}

	public int getPage_cnt() {
		return page_cnt;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
	
	private void dataCalc() {
		
		//현재 페이지를 토대로 페이징 목록 시작, 끝번호 계산
		endPageNum = (int)(Math.ceil((double)page / (double)page_cnt) * page_cnt);
		startPageNum = endPageNum - (page_cnt - 1);
		
		//계산한 마지막 페이지번호가 실제 총 게시물 개수로 환산한 마지막페이지 보다 작은지 검증
		int endPageNum_tmp = (int)(Math.ceil((double)total_cnt / (double)post_cnt));
		
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		//시작번호가 1일때 제외 무조건 출력
		prev= startPageNum == 1 ? false : true; 
		//마지막 페이지 번호*게시물 개수가 총게시물 수보다 작으면 출력
		next = endPageNum * post_cnt >= total_cnt ? false : true;  
	}



	

	
}
