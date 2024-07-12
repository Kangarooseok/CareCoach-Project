package com.carecoach.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carecoach.boardPagination.Pagination;
import com.carecoach.service.BoardService;
import com.carecoach.service.ChatbotService;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.LikesVO;
import com.carecoach.vo.PostsVO;

import com.carecoach.service.PostService;
import com.carecoach.vo.PostsVO;

@Controller
public class HomeController {
	
	@Autowired
	private BoardService boardServiceImpl;
	
	
<<<<<<< HEAD
	@RequestMapping("/")
	public ModelAndView main() {
		System.out.println("main메인컨트롤러");
		return new ModelAndView("index");
=======
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
>>>>>>> dev/devlogintest
	}
	
	@RequestMapping("/board/{category_id}")
    public String boardList(@PathVariable Integer category_id,
    		@RequestParam(defaultValue="1") int curPage,
            HttpServletRequest request,
            HttpSession session,
            Model model) throws Exception{
		System.out.println("/board/{category_id} 호출");
        PostsVO postsvo = new PostsVO();

		postsvo.setCategory_id(category_id);
		String loginid=(String)session.getAttribute("id");
		
		int listCnt = boardServiceImpl.selectPostCnt(category_id);
		
		Pagination pagination = new Pagination(listCnt, curPage);
		
		postsvo.setStartIndex(pagination.getStartIndex());
	
		postsvo.setCntPerPage(pagination.getPageSize());
		
        List<PostsVO> list = boardServiceImpl.selectPostList(postsvo);
               
        
        model.addAttribute("loginid",loginid);
        
        model.addAttribute("list", list);
        
        model.addAttribute("listCnt", listCnt);
        
        model.addAttribute("pagination", pagination);
        
        return returnPosts(category_id);
       
    }
	
	
	/* board */
    @RequestMapping(value="/board/writeForm.do")
    public String writeBoardForm() throws Exception{
    	
    	return "board/writeForm";
    }
	
    @Transactional
    @RequestMapping(value="/board/write.do")
    public String write(@ModelAttribute("postsVO") PostsVO postsVO, Model model) throws Exception{
    	
    	String content = postsVO.getContent();
    	if (content != null) {
    	    String str = content.replace("\r\n", "<br>");
    	    postsVO.setContent(str);
    	}
           	
    	boardServiceImpl.insertPost(postsVO);
        
        List<PostsVO> list = boardServiceImpl.selectPostList(postsVO);
        
        model.addAttribute("list", list);

        return "redirect:/board/" + postsVO.getCategory_id();
        
    }
    
   @Transactional
   @RequestMapping(value="/board/update.do")
    public String update(@ModelAttribute("postsVO") PostsVO postsVO, Model model) throws Exception{
	   	System.out.println("update.do 호출 됨"+postsVO.toString()); 
	   
	   	String content = postsVO.getContent();
    	if (content != null) {
    	    String str = content.replace("\r\n", "<br>");
    	    postsVO.setContent(str);
    	}
    	
        boardServiceImpl.updatePost(postsVO);
          
        List<PostsVO> list = boardServiceImpl.selectPostList(postsVO);
        
        model.addAttribute("list", list);
        
        return "redirect:/board/" + postsVO.getCategory_id();
        
    }
    
    
    
    @RequestMapping(value="/board/updateForm.do")
    public String updateForm(@ModelAttribute("postsVO") PostsVO postsVO, Model model, HttpServletRequest request) throws Exception{
       
    	int id = Integer.parseInt(request.getParameter("id"));
        
        postsVO.setId(id);
        
        PostsVO resultVO = boardServiceImpl.selectPostsById(postsVO);
        
        model.addAttribute("result", resultVO);
        
        return "board/updateForm";
        
    }
    
    @Transactional
    @RequestMapping(value="/board/delete.do")
     public String delete(@ModelAttribute("postsVO") PostsVO postsVO, Model model) throws Exception{
 	   	System.out.println("delete.do 호출 됨"+postsVO.toString()); 
 	   
 	   
         boardServiceImpl.deletePost(postsVO);
           
         List<PostsVO> list = boardServiceImpl.selectPostList(postsVO);
         
         model.addAttribute("list", list);
         
         return "redirect:/board/" + postsVO.getCategory_id();
         
     }
    
  
    
    
    @RequestMapping(value="/board/viewContent.do")
    public String viewForm(@ModelAttribute("postsVO") PostsVO postsVO, 
    	Model model, HttpSession session, HttpServletRequest request) throws Exception{
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        String loginid=(String)session.getAttribute("id");
		
        
        postsVO.setId(id);
        
        PostsVO resultVO = boardServiceImpl.selectPostsById(postsVO);
        
        boardServiceImpl.addViewCnt(postsVO.getId());

        model.addAttribute("loginid", loginid);
        
        model.addAttribute("result", resultVO);
        
        return "board/viewForm";
    }
	    	
	
	
	private String returnPosts(Integer categoryId) {
		System.out.println("returnPosts 호출 categoryId : "+categoryId);
		switch (categoryId) {
		case 1:
			 return "/board/aboutus";
		case 2:
			 return "/board/notice";
		case 3:
			 return "/board/freeboard";
		case 4:
			 return "/board/video";
		case 5:
			 return "/board/faq";
		case 6:
			 return "/board/qna";
		case 7:
			 return "/chatbot/chatbot";
		default:
			return "/";
		}
        
	}
	
	
	
}
