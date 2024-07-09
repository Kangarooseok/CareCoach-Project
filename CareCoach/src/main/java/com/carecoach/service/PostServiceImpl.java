package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carecoach.dao.PostDAO;
import com.carecoach.vo.PostsVO;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public List<PostsVO> getRecentPosts(int category_id) {
        return this.postDAO.selectRecentPosts(category_id);
    }

	@Override
	public List<PostsVO> selectRecentVideos(int categoryId) {
		return this.postDAO.selectRecentVideos(categoryId);
	}

	@Override
	public int countLikesByPostId(int postIdList) {
		// TODO Auto-generated method stub
		return this.postDAO.countLikesByPostId(postIdList);
	}

    // 기타 필요한 메서드 구현 가능
}
