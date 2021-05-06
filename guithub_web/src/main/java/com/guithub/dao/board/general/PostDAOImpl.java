package com.guithub.dao.board.general;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.guithub.mappers.board.general";
	
	
	//게시물 목록(페이징 + 서치)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		//한 페이지당 글을 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//매퍼에 전달하기 위해 해쉬데이터 생성
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".getList", data);
	}
	
	//게시물 목록(페이징+서치+댓글 카운트)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {
		//한 페이지당 글을 15개씩 나타내기 위한 카운트
		int startRowNum = 1+(page-1)*15;
		int endRowNum = page*15;
		//매퍼에 전달하기 위해 해쉬데이터 생성
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("startRowNum", startRowNum);
		data.put("endRowNum", endRowNum);
		
		data.put("field", field);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace+".getList_view", data);
	}
	
	//게시물 개수 카운트
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("field", field);
		data.put("keyword", keyword);
		
		
		return sql.selectOne(namespace + ".getCount", data);
	}
	
	//게시물 상세보기
	@Override
	public PostVO getDetail(int id) {
		
		return sql.selectOne(namespace+".getDetail", id);
	}

	
	//게시물 등록
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		System.out.println(sql.insert(namespace + ".regPost", vo));
		
	}
	
	//게시물 삭제
	@Override
	public int delPost(int id, String password) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", id);
		data.put("password", password);
			
		return sql.delete(namespace+".delPost", data);
	}
	
	//게시물 조회수 갱신
	@Override
	public void updateHit(int id) throws Exception {
		
		sql.update(namespace+".updateHit", id);
	}

	//게시물 패스워드 검증
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("password", password);
		boolean bool = sql.selectOne(namespace+".checkPwd", data);
		System.out.println("불린값 확인" + bool);
		
		return bool;
	}

	//게시물 수정
	@Override
	public void editPost(PostVO vo) throws Exception {
		
		sql.update(namespace+".editPost", vo);
	}

	//파일 정보 삽입
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		sql.insert(namespace+".regFile", vo);
	}

	//파일 정보 보기
	@Override
	public List<FileVO> getFile(int id) {

		return sql.selectList(namespace+".getFile", id);
	}

	//파일 삭제
	@Override
	public void delFile(int id) {
		
		sql.delete(namespace+".delFile", id);
	}





}
