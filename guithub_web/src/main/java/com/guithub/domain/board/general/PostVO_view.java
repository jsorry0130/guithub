package com.guithub.domain.board.general;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostVO_view {
	
	private int id;
	private String title;
	private String writer_id;
	private Date regdate;
	private int hit;
	private String reply_count;
	private String content;
	private String password;
	
	//실제 datatable에는 없는 속성. 오늘자 글인지 확인하기 위한 속성
	private boolean new_post = false;
		
	//getter and setter
	public int getId() {
		setNew_post();
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
	
	public String getReply_count() {
		return reply_count;
	}
	public void setReply_count(String reply_count) {
		this.reply_count = reply_count;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isNew_post() {
		return new_post;
	}
	
	public void setNew_post() {
		//현재 날짜 생성
		Date now = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		int simpleRegDate = Integer.parseInt(simpleDateFormat.format(regdate));
		int simpleNow = Integer.parseInt(simpleDateFormat.format(now));
		
		//현재 날짜와 게시물의 날짜가 같다면 오늘자 게시물
		if(simpleNow == simpleRegDate) {
			this.new_post = true;
		}
		
	}



	
	
}
