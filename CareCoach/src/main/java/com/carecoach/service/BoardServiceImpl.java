package com.carecoach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecoach.dao.BoardDAO;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDao;
	
	
	@Override
	public List<PostsVO> selectPostList(Integer categoryId) throws Exception {
		List<PostsVO> postList = null;
		postList = boardDao.selectPostList(categoryId);
		return postList;
	}
    
	
}
