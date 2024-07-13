package com.carecoach.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carecoach.service.mapper.BoardMapper;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.LikesVO;
import com.carecoach.vo.PostsVO;
import com.carecoach.vo.UsersVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PostsVO> selectPostList(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.selectPostList(postsVO);
    }


    @Override
    public int selectPostCnt(Integer categoryId) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.selectPostCnt(categoryId);
    }


    public void insertPost(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.insertPost(postsVO);
    }

    public void updatePost(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.updatePost(postsVO);
    }

    public void deletePost(PostsVO postsVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.deletePost(postsVO);
    }

    @Override
    public PostsVO selectPostsById(PostsVO postsVO) throws Exception {

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        return mapper.selectPostsById(postsVO);
    }

    @Override
    public void addViewCnt(Integer postsId) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        mapper.addViewCnt(postsId);
    }


    @Override
    public int is_Liked(LikesVO likesVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.is_Liked(likesVO);
    }


    @Override
    public int selectLikeCnt(Integer postsId) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        return mapper.selectLikeCnt(postsId);
    }


    @Override
    public void addLike(LikesVO likesVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.addLike(likesVO);
    }


    @Override
    public void delLike(LikesVO likesVO) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.delLike(likesVO);
    }


}
