package com.carecoach.service.mapper;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardMapper {
	
	// 게시물 리스트 조회
    public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
    
    
    
}
