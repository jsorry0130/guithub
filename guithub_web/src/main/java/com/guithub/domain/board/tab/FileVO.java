package com.guithub.domain.board.tab;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileVO {
	private int id;
	private String name;
	private int file_size;
	private int post_id;
	private Date regdate;
	//실제 테이블에는 없는 속성
	private String realName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		//setRealName();
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public String getRealName() {
		return realName;
	}
	
	//실제 테이블에는 없는 파일명을 위한 포맷팅된 regdate
	public void setRealName() {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyMMddHHmmss");
		String realFileName = name.split("\\.")[0]+"_" +simpleDate.format(regdate)+
				"."+name.split("\\.")[1];
		this.realName = realFileName;
	}
	
	
}
