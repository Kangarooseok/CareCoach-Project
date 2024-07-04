package com.carecoach.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carecoach.service.mapper.BoardMapper;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;
import com.carecoach.vo.UsersVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
    private SqlSession sqlSession;
	    
	@Override
	public List<PostsVO> selectPostList(Integer categoryId) throws Exception {
		 BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.selectPostList(categoryId);
	}
	
    public void insertBoard(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.insertBoard(postsVO);
    }
 
    public void updateBoard(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.updateBoard(postsVO);
    }
 
    public void deleteBoard(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.deleteBoard(postsVO);
    }

	@Override
	public PostsVO selectPostsById(PostsVO postsVO) throws Exception {

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

		return mapper.selectPostsById(postsVO);
	}
 

}
