package com.carecoach.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.carecoach.service.MemberService;
import com.carecoach.service.TermsService;
import com.carecoach.vo.TermsVO;

@Controller
public class TermsController {

	@Autowired
	private TermsService termsService;
	
	@GetMapping("/terms")
    public String showTerms(Model model) {
        TermsVO terms1 = termsService.getTermsById(1); // 1번 약관
        TermsVO terms2 = termsService.getTermsById(2); // 2번 약관
        
        model.addAttribute("terms1", terms1);
        model.addAttribute("terms2", terms2);
        return "member/terms"; //term.jsp
    }
	
}
