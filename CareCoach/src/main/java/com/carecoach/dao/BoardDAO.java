package com.carecoach.dao;

import java.util.List;

import com.carecoach.vo.PostsVO;

public interface BoardDAO {

	public List<PostsVO> selectPostList(Integer categoryId) throws Exception;

	PostsVO selectPostsById(PostsVO postsVO) throws Exception;

	void insertPost(PostsVO postsVO) throws Exception;

	void updatePost(PostsVO postsVO) throws Exception;

	void deletePost(PostsVO postsVO) throws Exception;

	void addViewCnt(Integer postsId) throws Exception;

}
