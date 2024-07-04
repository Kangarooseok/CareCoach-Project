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


	@Override
	public void insertBoard(PostsVO postsVO) throws Exception {
		boardDao.insertBoard(postsVO);
	}


	@Override
	public void updateBoard(PostsVO postsVO) throws Exception {
		boardDao.updateBoard(postsVO);
		
	}


	@Override
	public void deleteBoard(PostsVO postsVO) throws Exception {
		
		boardDao.deleteBoard(postsVO);
	}


	@Override
	public PostsVO selectPostsById(PostsVO postsVO) throws Exception {
		PostsVO resultVO = boardDao.selectPostsById(postsVO);

		return resultVO;
	}
    
	
}
