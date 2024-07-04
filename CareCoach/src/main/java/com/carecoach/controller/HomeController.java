package com.carecoach.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.carecoach.service.BoardService;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.PostsVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private BoardService boardServiceImpl;
	
	@RequestMapping("/")
	public ModelAndView main() {
		System.out.println("main메인컨트롤러");
		return new ModelAndView("index");
	}
	
	@RequestMapping("/board/aboutus")
    public String boardList(@ModelAttribute("categoryId") Integer categoryId, Model model) throws Exception{
                
        List<PostsVO> list = boardServiceImpl.selectPostList(categoryId);
        
        model.addAttribute("list", list);
        
        return "/board/aboutus";
    }
	
	
	
	@RequestMapping("/board/notice")
	public String notice(@ModelAttribute("categoryId") Integer categoryId, Model model) throws Exception{
	     System.out.println("공지사항 게시판 홈 컨트롤러");
	     List<PostsVO> list = boardServiceImpl.selectPostList(categoryId);
	        
        model.addAttribute("list", list);
	        
		return  "/board/notice";
	}
	

	
	@RequestMapping("/board/freeboard")
	public String freddboard(@ModelAttribute("categoryId") Integer categoryId, Model model) throws Exception{
		System.out.println("자유게시판 홈 컨트롤러");
     List<PostsVO> list = boardServiceImpl.selectPostList(categoryId);
	        
        model.addAttribute("list", list);
        
        return  "/board/freeboard";
	}
	
	@RequestMapping("/board/video")
	public String video(@ModelAttribute("categoryId") Integer categoryId, Model model) throws Exception{
		
		System.out.println("헬스 영상 홈 컨트롤러");
		
        List<PostsVO> list = boardServiceImpl.selectPostList(categoryId);
	        
        model.addAttribute("list", list);
        
        return  "/board/video";
	}
	
	/* board */
    @RequestMapping(value="/board/writeForm.do")
    public String writeBoardForm() throws Exception{
    	
    	return "board/writeForm";
    }
	
    @RequestMapping(value="/board/write.do")
    public String write(@ModelAttribute("postsVO") PostsVO postsVO, Model model) throws Exception{
        
        boardServiceImpl.insertBoard(postsVO);
        
        return "/board/notice";
    }
    
    @RequestMapping(value="/board/viewContent.do")
    public String viewForm(@ModelAttribute("postsVO") PostsVO postsVO, Model model, HttpServletRequest request) throws Exception{
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        postsVO.setId(id);
        
        PostsVO resultVO = boardServiceImpl.selectPostsById(postsVO);
        
        model.addAttribute("result", resultVO);
        
        return "board/viewForm";
    }
	
    
    /* 업데이트 */
    @RequestMapping(value="/board/updateboard.do")
    public String updateBoard(@ModelAttribute("postsVO") PostsVO postsVO, Model model) throws Exception{

        try{
            
            boardServiceImpl.updateBoard(postsVO);
            
        } catch (Exception e){
            e.printStackTrace();
        }        
        
        return "redirect:/";
        
    }
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/faq")
	public ModelAndView faq() {
		System.out.println("자주묻는질문 메인컨트롤러");
		return new ModelAndView("/qna/faq");
	}
	
	@RequestMapping("/qna")
	public ModelAndView qna() {
		System.out.println("자주 묻는 질문 메인컨트롤러");
		return new ModelAndView("/qna/qna");
	}
	
	@RequestMapping("/chatbot")
	public ModelAndView chatbot() {
		System.out.println("chatbot 메인컨트롤러");
		return new ModelAndView("/chatbot/chatbot");
	}
		
}
