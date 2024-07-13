package com.carecoach.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.carecoach.vo.PostsVO;

@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PostsVO> selectRecentPosts(int categoryId) {
        return sqlSession.selectList("selectRecentPosts", categoryId);
    }

    @Override
    public List<PostsVO> selectRecentVideos(int categoryId) {
        return sqlSession.selectList("selectRecentVideos", categoryId);
    }

    @Override
    public int countLikesByPostId(int postIdList) {
        return sqlSession.selectOne("countLikesByPostId", postIdList);
    }

}
