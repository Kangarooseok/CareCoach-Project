package com.carecoach.service;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardService {

    public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
    
	
}
