package com.carecoach.service;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardService {

    public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
 
	void insertBoard(PostsVO postsVO) throws Exception;
	
	void updateBoard(PostsVO postsVO) throws Exception;
	
	void deleteBoard(PostsVO postsVO) throws Exception;
	
	PostsVO selectPostsById(PostsVO postsVO) throws Exception;

}
