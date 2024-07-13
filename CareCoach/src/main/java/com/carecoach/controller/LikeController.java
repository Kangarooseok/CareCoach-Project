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
public class LikeController {

    @Autowired
    private BoardService boardServiceImpl;

    @Transactional
    @RequestMapping(value = "/board/addlike.do")
    public String addLike(@ModelAttribute("postsVO") PostsVO postsVO, Model model, HttpSession session) throws Exception {
        System.out.println("addlike.do 호출 됨" + postsVO.toString());

        String loginid = (String) session.getAttribute("id");
        int post_id = postsVO.getId();

        LikesVO likevo = new LikesVO();

        likevo.setPost_id(post_id);
        likevo.setUser_id(loginid);

        boardServiceImpl.addLike(likevo);

        return "redirect:/board/viewContent.do?id=" + postsVO.getId();

    }

    @Transactional
    @RequestMapping(value = "/board/deletelike.do")
    public String deleteLike(@ModelAttribute("postsVO") PostsVO postsVO, Model model, HttpSession session) throws Exception {
        System.out.println("addlike.do 호출 됨" + postsVO.toString());

        String loginid = (String) session.getAttribute("id");

        LikesVO likevo = new LikesVO();

        likevo.setPost_id(postsVO.getId());
        likevo.setUser_id(loginid);

        boardServiceImpl.delLike(likevo);

        return "redirect:/board/viewContent.do?id=" + postsVO.getId();

    }


}
