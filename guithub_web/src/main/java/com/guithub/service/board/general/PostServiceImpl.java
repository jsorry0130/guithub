package com.guithub.service.board.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.general.PostDAO;
import com.guithub.domain.board.general.FileVO;
import com.guithub.domain.board.general.PostVO;
import com.guithub.domain.board.general.PostVO_view;
import com.guithub.domain.board.general.ReplyVO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDao;
	
	//�Խù� ��� (����¡+��ġ+���ī��Ʈ)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {

		return postDao.getList_view(page, field, keyword);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		return postDao.getCount(field, keyword);
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return postDao.getDetail(id);
	}

	//�Խù� ���
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		postDao.regPost(vo);
		
	}
	
	//�Խù� ����
	@Override
	public int delPost(int id, String password) throws Exception {
		
		return postDao.delPost(id, password);
	}

	//�Խù� ��ȸ�� ����
	@Override
	public void updateHit(int id) throws Exception {
		
		postDao.updateHit(id);
	}

	//�Խù� �н����� ����
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		
		return postDao.checkPwd(id, password);
	}

	//�Խù� ����
	@Override
	public void editPost(PostVO vo) throws Exception {
		
		postDao.editPost(vo);
	}

	//���� ���� ����
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		postDao.regFile(vo);
	}

	//���� ���� ����
	@Override
	public List<FileVO> getFile(int id) {

		return postDao.getFile(id);
	}

	//���� ����
	@Override
	public void delFile(int id) {
		postDao.delFile(id);
	}









	
}
