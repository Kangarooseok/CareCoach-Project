package com.carecoach.service;

import com.carecoach.vo.PostsVO;

import java.util.List;

public interface PostService {

    List<PostsVO> getRecentPosts(int category_id);

    List<PostsVO> selectRecentVideos(int categoryId);

    int countLikesByPostId(int postIdList);

}
