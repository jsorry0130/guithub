package com.guithub.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostVO {
	
	private int id;
	private String title;
	private String content;
	private String writer_id;
	private Date regDate;
	private int hit;
	private String files;
	private String password;
	
	//���� datatable���� ���� �Ӽ�. ������ ������ Ȯ���ϱ� ���� �Ӽ�
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
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
		//���� ��¥ ����
		Date now = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		int simpleRegDate = Integer.parseInt(simpleDateFormat.format(regDate));
		int simpleNow = Integer.parseInt(simpleDateFormat.format(now));
		
		//���� ��¥�� �Խù��� ��¥�� ���ٸ� ������ �Խù�
		if(simpleNow == simpleRegDate) {
			this.new_post = true;
		}
		
	}
	
	
}
