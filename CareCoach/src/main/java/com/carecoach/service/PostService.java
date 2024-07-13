package com.carecoach.service;

import com.carecoach.vo.PostsVO;

import java.util.List;

public interface PostService {

    List<PostsVO> getRecentPosts(int categoryId);

    List<PostsVO> selectRecentVideos(int categoryId);

    int countLikesByPostId(int postIdList);

}
