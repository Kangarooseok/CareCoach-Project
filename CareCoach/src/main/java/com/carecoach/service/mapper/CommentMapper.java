package com.carecoach.service.mapper;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.CommentsVO;
import com.carecoach.vo.PostsVO;

public interface CommentMapper {
	
	// 댓글 리스트 조회
    public List<CommentsVO> commentList(Integer post_id) throws Exception;
    
    // 댓글 등록
    public void commentInsert(CommentsVO commentsVO) throws Exception;
    
    // 댓글 수정
    public void commentUpdate(CommentsVO commentsVO) throws Exception;
    
    // 댓글 삭제
    public void commentDelete(Integer post_id) throws Exception;

  
}
