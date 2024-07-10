package com.carecoach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecoach.dao.CommentDAO;
import com.carecoach.vo.CommentsVO;

@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO commentDAO;

	@Override
	public List<CommentsVO> commentList(Integer post_id) throws Exception {
		List<CommentsVO> commentList;
		commentList = commentDAO.commentList(post_id);
		return commentList;
	}

	@Override
	public void commentInsert(CommentsVO commentsVO) throws Exception {
		commentDAO.commentInsert(commentsVO);
	}

	@Override
	public void commentUpdate(CommentsVO commentsVO) throws Exception {
		commentDAO.commentUpdate(commentsVO);
	}

	@Override
	public void commentDelete(Integer post_id) throws Exception {
		commentDAO.commentDelete(post_id);
	}
	
	
    
	
}