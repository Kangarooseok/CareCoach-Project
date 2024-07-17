package com.carecoach.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carecoach.service.ChatbotService;
import com.carecoach.service.PostService;
import com.carecoach.vo.PostsVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	private PostService postService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		
		List<PostsVO> recentPosts1 = postService.getRecentPosts(2);
		List<PostsVO> recentPosts2 = postService.getRecentPosts(3);
		List<PostsVO> recentPosts3 = postService.selectRecentVideos(4);
		List<Integer> recentPosts4 = new ArrayList<Integer>();
		
		List<Integer> postIdList = new ArrayList<Integer>();
		for (PostsVO post : recentPosts3) {
		    postIdList.add(post.getId());
		}
		
		 for (int i = 0; i < postIdList.size(); i++) {
	            int postId = postIdList.get(i);
	            // 여기서 postId를 사용하여 필요한 작업을 수행합니다.
	            System.out.println("Post ID: " + postId);
	            recentPosts4.add(postService.countLikesByPostId(postId)); 
	        }


		System.out.println(recentPosts1);
		System.out.println(recentPosts2);
		System.out.println(recentPosts3);
		System.out.println(postIdList);
		System.out.println(recentPosts4);
		
		model.addAttribute("recentPosts1", recentPosts1);
		model.addAttribute("recentPosts2", recentPosts2);
		model.addAttribute("recentPosts3", recentPosts3);
		model.addAttribute("recentPosts4", recentPosts4);
		
		
		return "index";
	}
	
		
}
