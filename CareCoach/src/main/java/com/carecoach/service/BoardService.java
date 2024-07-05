package com.carecoach.service;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardService {

    public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
 
	void insertPost(PostsVO postsVO) throws Exception;
	
	void updatePost(PostsVO postsVO) throws Exception;
	
	void deletePost(PostsVO postsVO) throws Exception;
	
	PostsVO selectPostsById(PostsVO postsVO) throws Exception;
	
	void addViewCnt(Integer postsId) throws Exception;
	
}
