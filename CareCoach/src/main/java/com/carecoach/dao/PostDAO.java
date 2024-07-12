package com.carecoach.dao;

import java.util.List;
import com.carecoach.vo.PostsVO;

public interface PostDAO {
    
    // 특정 카테고리의 최근 게시물 5개를 가져오는 메서드
	List<PostsVO> selectRecentPosts(int categoryId);
	List<PostsVO> selectRecentVideos(int categoryId);
	int countLikesByPostId(int postIdList);

}
