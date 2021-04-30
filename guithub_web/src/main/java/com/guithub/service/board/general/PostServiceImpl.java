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
	private PostDAO dao;
	
	//�Խù� ��� (����¡ + ��ġ)
	@Override
	public List<PostVO> getList(int page, String field, String keyword) throws Exception {
		
		return dao.getList(page, field, keyword);
	}
	
	//�Խù� ��� (����¡+��ġ+���ī��Ʈ)
	@Override
	public List<PostVO_view> getList_view(int page, String field, String keyword) throws Exception {

		return dao.getList_view(page, field, keyword);
	}
	
	//�Խù� ���� ī��Ʈ
	@Override
	public int getCount(String field, String keyword) throws Exception {
		
		return dao.getCount(field, keyword);
	}
	
	//�Խù� �󼼺���
	@Override
	public PostVO getDetail(int id) throws Exception {
		
		return dao.getDetail(id);
	}

	//�Խù� ���
	@Override
	public void regPost(PostVO vo) throws Exception {
		
		dao.regPost(vo);
		
	}
	
	//�Խù� ����
	@Override
	public int delPost(int id, String password) throws Exception {
		
		return dao.delPost(id, password);
	}

	//��� ���
	@Override
	public List<ReplyVO> getListReply(int post_id) throws Exception {
		
		return dao.getListReply(post_id);
	}

	//��� ���
	@Override
	public void regReply(ReplyVO vo) throws Exception {
		
		dao.regReply(vo);	
	}

	//��� ����
	@Override
	public int delReply(int id, String password) throws Exception {
		
		return dao.delReply(id, password);
	}

	//��� ���� ī��Ʈ
	@Override
	public int getCountReply(int id) throws Exception {
		
		return dao.getCountReply(id);
	}

	//�Խù� ��ȸ�� ����
	@Override
	public void updateHit(int id) throws Exception {
		
		dao.updateHit(id);
	}

	//�Խù� �н����� ����
	@Override
	public boolean checkPwd(int id, String password) throws Exception {
		
		return dao.checkPwd(id, password);
	}

	//�Խù� ����
	@Override
	public void updatePost(PostVO vo) throws Exception {
		
		dao.updatePost(vo);
	}

	//���� ���� ����
	@Override
	public void regFile(FileVO vo) throws Exception {
		
		dao.regFile(vo);
	}

	//���� ���� ����
	@Override
	public List<FileVO> getFile(int id) {

		return dao.getFile(id);
	}









	
}
