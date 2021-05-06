package com.guithub.domain.board.tab;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostVO {
	
	private int id;
	private String title;
	private String content;
	private String writer_id;
	private Date regdate;
	private int hit;
	
	//���� datatable���� ���� �Ӽ�. ������ ������ Ȯ���ϱ� ���� �Ӽ�
	private boolean new_post = false;
		
	//getter and setter
	public int getId() {
		//setNew_post();
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

	public boolean isNew_post() {
		return new_post;
	}
	
	public void setNew_post() {
		//���� ��¥ ����
		Date now = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		int simpleRegDate = Integer.parseInt(simpleDateFormat.format(regdate));
		int simpleNow = Integer.parseInt(simpleDateFormat.format(now));
		
		//���� ��¥�� �Խù��� ��¥�� ���ٸ� ������ �Խù�
		if(simpleNow == simpleRegDate) {
			this.new_post = true;
		}
		
	}

	
	
}
