package com.carecoach.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carecoach.service.mapper.BoardMapper;
import com.carecoach.service.mapper.CommentMapper;
import com.carecoach.vo.CommentsVO;
import com.carecoach.vo.PostsVO;


@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CommentsVO> commentList(Integer post_id) throws Exception {
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        return mapper.commentList(post_id);
    }

    @Override
    public void commentInsert(CommentsVO commentsVO) throws Exception {
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.commentInsert(commentsVO);
    }

    @Override
    public void commentUpdate(CommentsVO commentsVO) throws Exception {
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.commentUpdate(commentsVO);
    }

    @Override
    public void commentDelete(Integer post_id) throws Exception {
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.commentDelete(post_id);
    }


}
