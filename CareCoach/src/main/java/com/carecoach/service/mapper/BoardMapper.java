package com.carecoach.service.mapper;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardMapper {
	
	// 게시물 리스트 조회
    public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
    
    // 게시물 등록
    public void insertBoard(PostsVO postsVO) throws Exception;
    
    // 게시물 수정
    public void updateBoard(PostsVO postsVO) throws Exception;
    
    // 게시물 삭제
    public void deleteBoard(PostsVO postsVO) throws Exception;
 
    // 게시물 조회
    public PostsVO selectPostsById(PostsVO postsVO) throws Exception;
    
}
