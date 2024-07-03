package com.carecoach.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	private BoardService boardService;
	
	@RequestMapping("/")
	public ModelAndView main() {
		System.out.println("main메인컨트롤러");
		return new ModelAndView("index");
	}
	
	@RequestMapping("/board/aboutus")
    public String boardList(@ModelAttribute("categoryId") Integer categoryId, Model model) throws Exception{
                
        List<PostsVO> list = boardService.selectPostList(categoryId);
        
        model.addAttribute("list", list);
        
        return "/board/aboutus";
    }
	
	
	@RequestMapping("/board/notice")
	public ModelAndView notice() {
		System.out.println("notice메인컨트롤러");
		return new ModelAndView("/board/notice");
	}
	
	@RequestMapping("/board/freeboard")
	public ModelAndView board() {
		System.out.println("board메인컨트롤러");
		return new ModelAndView("/board/freeboard");
	}
	
	@RequestMapping("/board/video")
	public ModelAndView video() {
		System.out.println("video메인컨트롤러");
		return new ModelAndView("/board/video");
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
