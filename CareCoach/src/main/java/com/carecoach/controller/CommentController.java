package com.carecoach.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.carecoach.boardPagination.Pagination;
import com.carecoach.service.BoardService;
import com.carecoach.service.ChatbotService;
import com.carecoach.service.CommentService;
import com.carecoach.vo.CategoryVO;
import com.carecoach.vo.CommentsVO;
import com.carecoach.vo.PostsVO;
import com.carecoach.vo.UsersVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	/**
     * 댓글 등록(Ajax)
     * @param boardVO
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/board/addComment.do")
    @ResponseBody
    public String ajax_addComment(@ModelAttribute("CommentsVO") CommentsVO commentsVO, HttpServletRequest request) throws Exception{
        
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("id");
        
        try{
        
        	commentsVO.setUser_id(user_id);        
        	commentService.commentInsert(commentsVO);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return "success";
    }
    
    /**
     * 게시물 댓글 불러오기(Ajax)
     * @param boardVO
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/board/commentList.do", produces="application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity<List<CommentsVO>> ajax_commentList(@ModelAttribute("commentsVO")
    	CommentsVO commentsVO, HttpServletRequest request) throws Exception{
        
        HttpHeaders responseHeaders = new HttpHeaders();
        ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
        
        // 해당 게시물 댓글
        List<CommentsVO> list = commentService.commentList(commentsVO.getPost_id());
        
        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                HashMap hm = new HashMap();
                hm.put("id", list.get(i).getId());
                hm.put("user_id", list.get(i).getUser_id());
                hm.put("content", list.get(i).getContent());
                
                hmlist.add(hm);
            }
            
        }
        
        JSONArray json = new JSONArray(hmlist);        
        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
        
    }
	
}
