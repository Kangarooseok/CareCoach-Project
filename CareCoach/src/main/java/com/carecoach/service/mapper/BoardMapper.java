package com.carecoach.service.mapper;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.LikesVO;
import com.carecoach.vo.PostsVO;

public interface BoardMapper {
	
	// 게시물 리스트 조회
    public List<PostsVO> selectPostList(PostsVO postsVO) throws Exception;
    
    //게시물 개수
    public int selectPostCnt(Integer categoryId) throws Exception;
    
    // 게시물 등록
    public void insertPost(PostsVO postsVO) throws Exception;
    
    // 게시물 수정
    public void updatePost(PostsVO postsVO) throws Exception;
    
    // 게시물 삭제
    public void deletePost(PostsVO postsVO) throws Exception;
 
    // 게시물 조회
    public PostsVO selectPostsById(PostsVO postsVO) throws Exception;
    
    //게시물 조회수
    public void addViewCnt(Integer postsId) throws Exception;
    
    //좋아요 총 개수 조회
    public void selectLikeCnt(Integer postsId) throws Exception;

    //좋아요 추가
    public void addLike(LikesVO likesVO) throws Exception;
    
    //좋아요 삭제
    public void delLike(LikesVO likesVO) throws Exception;
    
    
    
}
