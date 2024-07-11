package com.carecoach.service;

import java.util.List;

import com.carecoach.vo.LikesVO;
import com.carecoach.vo.PostsVO;

public interface BoardService {

    public List<PostsVO> selectPostList(PostsVO postsVO) throws Exception;
    
    public int selectPostCnt(Integer categoryId) throws Exception;
 
	void insertPost(PostsVO postsVO) throws Exception;
	
	void updatePost(PostsVO postsVO) throws Exception;
	
	void deletePost(PostsVO postsVO) throws Exception;
	
	PostsVO selectPostsById(PostsVO postsVO) throws Exception;
	
	void addViewCnt(Integer postsId) throws Exception;
	
	//좋아요 여부 확인
    public int is_Liked(PostsVO postsVO) throws Exception;
	
	 //게시글의 좋아요 총 개수 조회
    public int selectLikeCnt(Integer postsId) throws Exception;
	
	 //좋아요 추가
	 void addLike(PostsVO postsVO) throws Exception;
	    
	 //좋아요 삭제
	 void delLike(PostsVO postsVO) throws Exception;
    
}
