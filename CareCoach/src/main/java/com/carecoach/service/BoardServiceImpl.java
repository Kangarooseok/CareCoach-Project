package com.carecoach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecoach.dao.BoardDAO;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.LikesVO;
import com.carecoach.vo.PostsVO;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDao;
		
	
	@Override
	public List<PostsVO> selectPostList(PostsVO postsVO) throws Exception {
		List<PostsVO> postList = null;
		postList = boardDao.selectPostList(postsVO);
		return postList;
	}

	

	@Override
	public int selectPostCnt(Integer categoryId) throws Exception {
		int postcnt = 0;
		postcnt = boardDao.selectPostCnt(categoryId);
		return postcnt;
	}

	@Override
	public void insertPost(PostsVO postsVO) throws Exception {
		boardDao.insertPost(postsVO);
	}


	@Override
	public void updatePost(PostsVO postsVO) throws Exception {
		boardDao.updatePost(postsVO);
		
	}


	@Override
	public void deletePost(PostsVO postsVO) throws Exception {
		
		boardDao.deletePost(postsVO);
	}


	@Override
	public PostsVO selectPostsById(PostsVO postsVO) throws Exception {
		PostsVO resultVO = boardDao.selectPostsById(postsVO);
		
		return resultVO;
	}


	@Override
	public void addViewCnt(Integer postsId) throws Exception {
		boardDao.addViewCnt(postsId);
	}


	
	

	@Override
	public int is_Liked(LikesVO likesVO) throws Exception {
		return boardDao.is_Liked(likesVO);
	}


	@Override
	public int selectLikeCnt(Integer postsId) throws Exception {
		return boardDao.selectLikeCnt(postsId);
	}



	@Override
	public void addLike(LikesVO likesVO) throws Exception {
		boardDao.addLike(likesVO);
	}



	@Override
	public void delLike(LikesVO likesVO) throws Exception {
		boardDao.delLike(likesVO);
	}
    
	
	
}
