package com.carecoach.dao;

import java.util.List;

import com.carecoach.vo.CommentsVO;
import com.carecoach.vo.PostsVO;

public interface CommentDAO {

	// 댓글 리스트 조회
    public List<CommentsVO> commentList(Integer post_id) throws Exception;
    
    // 댓글 등록
    void commentInsert(CommentsVO commentsVO) throws Exception;
    
    // 댓글 수정
    void commentUpdate(CommentsVO commentsVO) throws Exception;
    
    // 댓글 삭제
    void commentDelete(Integer post_id) throws Exception;


}
