package com.guithub.dao.board.tab;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired
	private SqlSession sql;
	private static String namespace = "com.guithub.mappers.board.tab";
	
	//게시물 총 개수
	@Override
	public int getPostCnt(String field, String keyword) throws Exception{
		
		HashMap<String, Object> data = new HashMap<String, Object >();
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace+".getPostCnt", data);
	}
	
	//게시물 목록(페이징 + 서치)
	@Override
	public List<PostVO_view> getList(int page, String field, String keyword) throws Exception{
		
		//한 페이지당 게시물 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		
		HashMap<String, Object> data = new HashMap<String, Object >();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".getList_view", data);
	}

	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) throws Exception {

		return sql.selectOne(namespace+".getDetail", id);
	}

	//게시물 조회수 갱신
	@Override
	public void updateHit(int id) throws Exception {
		
		sql.update(namespace+".updateHit", id);
	}

	//게시물 첨부파일 목록
	@Override
	public List<FileVO> getFileList(int id) throws Exception {
		
		return sql.selectList(namespace+".getFileList", id);
	}

	//게시물 등록
	@Override
	public void regPost(PostVO post) {
		sql.insert(namespace+".regPost", post);
	}

	//게시물 첨부파일 등록
	@Override
	public void regFile(FileVO filedata) {
		sql.insert(namespace+".regFile", filedata);
	}

	//게시물 삭제
	@Override
	public void delPost(int id) throws Exception {
		sql.delete(namespace+".delPost", id);
	}

	//게시물 수정
	@Override
	public void editPost(PostVO post) throws Exception {
		sql.update(namespace+".editPost", post);
	}
	
	//게시물 첨부파일 삭제
	@Override
	public void delFile(int delFile_id) throws Exception {
		sql.delete(namespace+".delFile", delFile_id);
	}
}
