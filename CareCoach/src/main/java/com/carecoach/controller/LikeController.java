package com.carecoach.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carecoach.service.BoardService;
import com.carecoach.vo.LikesVO;
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
        int postId = postsVO.getId();

        LikesVO likevo = new LikesVO();

        likevo.setPostId(postId);
        likevo.setUserId(loginid);

        boardServiceImpl.addLike(likevo);

        return "redirect:/board/viewContent.do?id=" + postsVO.getId();

    }

    @Transactional
    @RequestMapping(value = "/board/deletelike.do")
    public String deleteLike(@ModelAttribute("postsVO") PostsVO postsVO, Model model, HttpSession session) throws Exception {
        System.out.println("addlike.do 호출 됨" + postsVO.toString());

        String loginid = (String) session.getAttribute("id");

        LikesVO likevo = new LikesVO();

        likevo.setPostId(postsVO.getId());
        likevo.setUserId(loginid);

        boardServiceImpl.delLike(likevo);

        return "redirect:/board/viewContent.do?id=" + postsVO.getId();

    }


}
