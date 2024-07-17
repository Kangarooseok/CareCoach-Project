package com.carecoach.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.carecoach.service.CommentService;
import com.carecoach.vo.CommentsVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


   //게시물 등록
    @RequestMapping(value = "/board/addComment.do")
    @ResponseBody
    public String ajax_addComment(@ModelAttribute("CommentsVO") CommentsVO commentsVO, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("id");

        try {

            commentsVO.setUserId(userId);
            commentService.commentInsert(commentsVO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    //게시물 불러오기
    @RequestMapping(value = "/board/commentList.do", produces = "application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity<List<CommentsVO>> ajax_commentList(@ModelAttribute("commentsVO")
                                                             CommentsVO commentsVO,
                                                             HttpSession session,
                                                             Model model,
                                                             HttpServletRequest request) throws Exception {

        HttpHeaders responseHeaders = new HttpHeaders();
        ArrayList<HashMap> hmlist = new ArrayList<HashMap>();

        // 해당 게시물 댓글
        List<CommentsVO> list = commentService.commentList(commentsVO.getPostId());

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap hm = new HashMap();
                hm.put("id", list.get(i).getId());
                hm.put("userId", list.get(i).getUserId());
                // content의 줄넘김 제거
                String contentWithoutNewlines = list.get(i).getContent().replaceAll("\\r|\\n", "");
                hm.put("content", contentWithoutNewlines);
                hm.put("postId", list.get(i).getPostId());
                hm.put("updatedDt", list.get(i).getUpdatedDt());
                hmlist.add(hm);
            }

        }

        String loginid = (String) session.getAttribute("id");
        model.addAttribute("loginid", loginid);

        JSONArray json = new JSONArray(hmlist);
        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);

    }
    // 댓글 삭제
    @RequestMapping(value = "/board/delComment.do", method = RequestMethod.POST)
    @ResponseBody
    public String ajax_delComment(@ModelAttribute("CommentsVO") CommentsVO commentsVO, HttpServletRequest request) {
        System.out.println("delComment.do 호출 됨" + commentsVO.toString());
        try {
            commentService.commentDelete(commentsVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    // 댓글 수정
    @RequestMapping(value = "/board/updateComment.do", method = RequestMethod.POST)
    @ResponseBody
    public String ajax_updateComment(@ModelAttribute("CommentsVO") CommentsVO commentsVO, HttpServletRequest request) {
        System.out.println("updateComment.do 호출 됨" + commentsVO.toString());
        try {
            commentService.commentUpdate(commentsVO);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


}
