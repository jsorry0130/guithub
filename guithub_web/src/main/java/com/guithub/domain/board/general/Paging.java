package com.guithub.domain.board.general;

public class Paging {
	
	public Paging(int page, int total_cnt) {
		this.page = page;
		this.total_cnt = total_cnt;
		
		dataCalc();
	}

	//���� ������
	private int page;
	
	//�Խù� �� ����
	private int total_cnt;
	
	//�� �������� ��Ÿ�� �Խù� ����
	private int post_cnt = 15;
	
	//����¡ ��Ͽ� ��Ÿ�� ������ ����
	private int page_cnt= 10;
	
	//������ ��, ���� ��ȣ 
	private int endPageNum;
	private int startPageNum;
	
	//����, ���� ��ư ǥ�� ����
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
		
		//���� �������� ���� ����¡ ��� ����, ����ȣ ���
		endPageNum = (int)(Math.ceil((double)page / (double)page_cnt) * page_cnt);
		startPageNum = endPageNum - (page_cnt - 1);
		
		//����� ������ ��������ȣ�� ���� �� �Խù� ������ ȯ���� ������������ ���� ������ ����
		int endPageNum_tmp = (int)(Math.ceil((double)total_cnt / (double)post_cnt));
		
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		//���۹�ȣ�� 1�϶� ���� ������ ���
		prev= startPageNum == 1 ? false : true; 
		//������ ������ ��ȣ*�Խù� ������ �ѰԽù� ������ ������ ���
		next = endPageNum * post_cnt >= total_cnt ? false : true;  
	}



	

	
}
