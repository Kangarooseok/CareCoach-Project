package com.carecoach.dao;

import java.util.List;

import com.carecoach.vo.PostsVO;

public interface BoardDAO {

	public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
    
	PostsVO selectPostsById(PostsVO postsVO) throws Exception;

	void insertBoard(PostsVO postsVO) throws Exception;
	
	void updateBoard(PostsVO postsVO) throws Exception;
	
	void deleteBoard(PostsVO postsVO) throws Exception;
	

}
