package com.carecoach.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import com.carecoach.service.MemberService;
import com.carecoach.vo.UsersVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class memberController {

    @Autowired
    private MemberService memberService;
    
    
    
    @RequestMapping(value = "/mypage")
    public ModelAndView mypage(HttpServletResponse response, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView("mypage/mypage");
        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("/login");
            return null;
        }
        UsersVO user = memberService.loginCheck(userId);

        mav.addObject("id", user.getUserId());
        mav.addObject("name", user.getName());
        mav.addObject("email", user.getEmail());
        mav.addObject("bio", user.getBio());
        mav.addObject("profile_image", user.getProfileImg());

        return mav;
    }

    @GetMapping(value = "/checkIdAvailability", produces = "application/json")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam String userId) {
        System.out.println("유저 아이디 요청 " + userId);  
        boolean isAvailable = memberService.isUserIdAvailable(userId);
        System.out.println("아이디 체크 : " + userId + ", 사용 가능: " + isAvailable);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("available", isAvailable);
        return response;
    }
    
    @GetMapping("/checkId")
    public String checkIdPage() {
        return "member/checkId";
    }

    @PostMapping("/join_process")
    public String joinProcess(UsersVO user, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            memberService.registerUser(user);
            out.println("<script>alert('회원가입이 완료되었습니다.'); location.href='/login'; </script>");
        } catch (DuplicateKeyException e) {
            out.println("<script>alert('" + e.getMessage() + "'); history.back();</script>");
        } catch (Exception e) {
            System.err.println("예외 발생: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            out.println("<script>alert('회원가입 중 오류가 발생했습니다.'); history.back();</script>");
        }
        
        return null;
    }

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("member/login");
    }

    @RequestMapping("/findId")
    public String findId() {
        return "member/findId";
    }

    @RequestMapping("/findPwd")
    public String findPwd() {
        return "member/findPwd";
    }
    
    @RequestMapping("/findPwdResult")
    public String findPwdResult() {
        return "member/login";
    }

    @RequestMapping(value = "/findPwdResult", method = RequestMethod.POST)
    public ModelAndView findPwdResult(String userId, String email, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if (email == null) {
            response.sendRedirect("/login");
            return null;
        }

        UsersVO user = this.memberService.findId(email);

        if (user == null || !user.getEmail().equals(email) || !user.getUserId().equals(userId)) {
            out.println("<script>");
            out.println("alert('해당 이메일과 아이디로 등록된 사용자가 없습니다.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return null;
        } else {
            session.setAttribute("email",email);
            ModelAndView mav = new ModelAndView("member/findPwd");
            mav.addObject("resetMode", true);
            mav.addObject("userId", user.getUserId());
            return mav;
        }
    }

    @PostMapping("/resetPwd")
    public ModelAndView resetPwd(String newPassword, String confirmPassword, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = (String)session.getAttribute("email");
        UsersVO m = this.memberService.userCheck(email);

        m.setPassword(confirmPassword);
        this.memberService.changepw(m);

        out.println("<script>");
        out.println("alert('비밀번호가 변경되었습니다.');");
        out.println("location.href='/login'");
        out.println("</script>");

        return null;
    }

    @RequestMapping("/findIdResult")
    public ModelAndView findIdResult(String name, String email, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UsersVO m = this.memberService.findId(email);

        if (m == null || !m.getEmail().equals(email) || !m.getName().equals(name)) {
            out.println("<script>");
            out.println("alert('해당 이메일과 이름으로 등록된 아이디가 없습니다.');");
            out.println("history.back();");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("alert('"+m.getName()+"님의 아이디는 \""+m.getUserId()+"\"입니다.');");
            out.println("location.href='/login'");
            out.println("</script>");
        }
        return null;
    }

    @RequestMapping("/member_logout")
    public ModelAndView member_logout(HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        session.invalidate();

        out.println("<script>");
        out.println("alert('로그아웃 되었습니다.');");
        out.println("location='/';");
        out.println("</script>");

        return null;
    }

    public static boolean isLogin(HttpSession session, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        String id = (String)session.getAttribute("id");

        if (id == null) {
            out.println("<script>");
            out.println("alert('다시 로그인 하세요.');");
            out.println("location='member_login';");
            out.println("</script>");

            return false;
        }
        return true;
    }
    
    @PostMapping("/member_login_ok")
    public ModelAndView member_login_ok(String userId, String password,
    		HttpServletResponse response, HttpSession session) throws Exception {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UsersVO m = this.memberService.loginCheck(userId);

        if (m == null) {
            out.println("<script>");
            out.println("alert('가입 안된 회원입니다.');");
            out.println("history.back();");
            out.println("</script>");
        } else {
            try {
                if (memberService.verifyPassword(password, m.getPassword())) {
                    session.setAttribute("id", userId);
                    session.setMaxInactiveInterval(1800);
                    return new ModelAndView("redirect:/");
                } else {
                    out.println("<script>");
                    out.println("alert('비밀번호가 다릅니다.');");
                    out.println("history.go(-1);");
                    out.println("</script>");        		
                }
            } catch (NoSuchAlgorithmException e) {
                out.println("<script>");
                out.println("alert('로그인 처리 중 오류가 발생했습니다.');");
                out.println("history.go(-1);");
                out.println("</script>");
            }
        }
        return null;
    }

    @RequestMapping("/changePW")
    public String changePW() {
        System.out.print("changePW");
        return "mypage/changePW";
    }

    @RequestMapping("/chgpw")
    public ModelAndView chgpw() {
        return new ModelAndView("mypage/changePW");
    }
    
    @PostMapping("/chgpw_ok")
    public ModelAndView chgpw_ok(String password, String newpassword, HttpServletResponse response, HttpSession session) throws Exception {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String)session.getAttribute("id");
        UsersVO user = memberService.loginCheck(id);

        try {
            if (memberService.verifyPassword(password, user.getPassword())) {
                user.setPassword(newpassword);  // 새 비밀번호를 설정
                memberService.changepw(user);   // 서비스에서 해싱 처리

                out.println("<script>");
                out.println("alert('비밀번호가 변경되었습니다.');");
                out.println("location.href='/mypage'");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('현재 비밀번호가 틀립니다.');");
                out.println("history.back();");
                out.println("</script>");
            }
        } catch (Exception e) {
            System.err.println("Password change error: " + e.getMessage());
            e.printStackTrace();
            out.println("<script>");
            out.println("alert('비밀번호 변경 중 오류가 발생했습니다.');");
            out.println("history.back();");
            out.println("</script>");
        }

        return null;
    }

    @RequestMapping("/delmem")
    public String delmem() {
        return "mypage/delmem";
    }
    
    @PostMapping("/del_mem_ok")
    public ModelAndView del_mem_ok(String password, HttpServletResponse response, HttpSession session) throws Exception {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) session.getAttribute("id");
        UsersVO user = memberService.loginCheck(id);

        try {
            if (memberService.verifyPassword(password, user.getPassword())) {
                memberService.del_mem(id);
                session.invalidate();

                out.println("<script>");
                out.println("alert('회원 탈퇴했습니다.');");
                out.println("location='/';");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('비밀번호가 틀립니다.');");
                out.println("history.back();");
                out.println("</script>");
            }
        } catch (NoSuchAlgorithmException e) {
            out.println("<script>");
            out.println("alert('회원 탈퇴 처리 중 오류가 발생했습니다.');");
            out.println("history.back();");
            out.println("</script>");
        }

        return null;
    }

    @PostMapping("/saveProfile2")
    public void saveProfile(@RequestParam("bio") String bioContent, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }

        UsersVO user = new UsersVO();
        user.setUserId(userId);
        user.setBio(bioContent);
        memberService.updatebio(user);

        out.println("<script>");
        out.println("alert('자기소개글 저장되었습니다.');");
        out.println("location.href='/mypage';");
        out.println("</script>");
    }
    
    @PostMapping("/saveProfile")
    public void saveProfile(@RequestParam(value = "profileImg", required = false) MultipartFile profileImage,
            HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String userId = (String) session.getAttribute("id");
        
        UsersVO user = new UsersVO();
        user.setUserId(userId);

        if (profileImage != null && !profileImage.isEmpty()) {
            String uploadDirectory = session.getServletContext().getRealPath("/") + "resources/upload";
            Path uploadPath = Paths.get(uploadDirectory);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            String filePathString = filePath.toString();
            
            System.out.println(uploadDirectory);
            System.out.println(filePath);

            if (Files.isDirectory(filePath)) {
                System.err.println("파일 경로가 디렉토리입니다: " + filePath.toString());
                return;
            }

            try {
                Files.createFile(filePath);
                profileImage.transferTo(filePath.toFile());
                System.out.println(fileName);

                user.setProfileImg("/resources/upload/" + fileName);
                memberService.updateProfilePicPath(user);
                
                out.println("<script>");
                out.println("alert('프로필이 저장되었습니다.');");
                out.println("location.href='/mypage';");
                out.println("</script>");
            } catch (IOException e) {
                System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
                out.println("<script>");
                out.println("alert('파일 저장 중 오류가 발생했습니다.');");
                out.println("location.href='/mypage';");
                out.println("</script>");
            }
        } else {
            out.println("<script>");
            out.println("alert('프로필이 저장되었습니다.');");
            out.println("location.href='/mypage';");
            out.println("</script>");
        }
    }
}