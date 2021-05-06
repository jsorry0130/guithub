package com.guithub.service.board.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guithub.dao.board.tab.PostDAO;
import com.guithub.domain.board.tab.FileVO;
import com.guithub.domain.board.tab.PostVO;
import com.guithub.domain.board.tab.PostVO_view;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDao;
	
	//�Խù� �� ����
	@Override
	public int getPostCnt(String field, String keyword) throws Exception {
		
		return postDao.getPostCnt(field, keyword);
	}
	
	//�Խù� ��� (����¡ + ��ġ)
	@Override
	public List<PostVO_view> getList(int page, String field, String keyword) throws Exception {
		
		return postDao.getList(page, field, keyword);
	}

	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return postDao.getDetail(id);
	}

	//�Խù� ��ȸ�� ����
	@Override
	public void updateHit(int id) throws Exception {
		
		postDao.updateHit(id);
	}

	//�Խù� ÷������ ���
	@Override
	public List<FileVO> getFileList(int id) throws Exception {
		
		return postDao.getFileList(id);
	}

	//�Խù� ���
	@Override
	public void regPost(PostVO post) throws Exception {
		postDao.regPost(post);
	}

	//�Խù� ÷������ ���
	@Override
	public void regFile(FileVO filedata) throws Exception {
		
		postDao.regFile(filedata);
	}

	//�Խù� ����
	@Override
	public void delPost(int id) throws Exception {
		postDao.delPost(id);
	}

	//�Խù� ����
	@Override
	public void editPost(PostVO post) throws Exception {
		postDao.editPost(post);
	}

	//�Խù� ÷������ ����
	@Override
	public void delFile(int delFile_id) throws Exception {
		postDao.delFile(delFile_id);
	}
	

}
