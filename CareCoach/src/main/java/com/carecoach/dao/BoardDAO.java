package com.carecoach.dao;

import java.util.List;

import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

public interface BoardDAO {

	public List<PostsVO> selectPostList(Integer categoryId) throws Exception;
    
	

}
