package com.carecoach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

@Controller
public class memberController {

    @Autowired
    private MemberService memberService;

    // 로그인 화면
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("member/login");
    }

    // 아이디 찾기
    @RequestMapping("/findId")
    public String findId() {
        return "member/findId";
    }

    // 마이페이지
    @RequestMapping(value = "/mypage")
    public ModelAndView mypage(HttpServletResponse response, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView("mypage/mypage");
        String userId = (String) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("/login");
            return null;
        }
        UsersVO user = memberService.loginCheck(userId);

        mav.addObject("id", user.getUser_id());
        mav.addObject("name", user.getName());
        mav.addObject("email", user.getEmail());
        mav.addObject("bio", user.getBio());
        mav.addObject("profile_image", user.getProfile_image());

        return mav;
    }

    // 로그아웃
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

    public static boolean isLogin(HttpSession session, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();
        String id = (String) session.getAttribute("id");

        if (id == null) {
            out.println("<script>");
            out.println("alert('다시 로그인 하세요.');");
            out.println("location='member_login';");
            out.println("</script>");

            return false;
        }
        return true;
    }

    // 로그인 확인
    @PostMapping("/member_login_ok")
    public ModelAndView member_login_ok(String user_id, String password, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UsersVO m = this.memberService.loginCheck(user_id);

        if (m == null) {
            out.println("<script>");
            out.println("alert('가입 안된 회원입니다.');");
            out.println("history.back();");
            out.println("</script>");
        } else {
            if (!m.getPassword().equals(password)) {
                out.println("<script>");
                out.println("alert('비번이 다릅니다.');");
                out.println("history.go(-1);");
                out.println("</script>");
            } else {
                session.setAttribute("id", user_id);
                session.setAttribute("name", m.getName());
                session.setAttribute("email", m.getEmail());
                session.setAttribute("bio", m.getBio());
                session.setMaxInactiveInterval(1800);
                return new ModelAndView("redirect:/");
            }
        }
        return null;
    }

    @RequestMapping("/chgpw")
    public ModelAndView chgpw() {
        return new ModelAndView("mypage/changePW");
    }

    // 비번 변경
    @PostMapping("/chgpw_ok")
    public ModelAndView chgpw_ok(String password, String newpassword, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) session.getAttribute("id");
        UsersVO m = this.memberService.loginCheck(id);

        if (!m.getPassword().equals(password)) {
            out.println("<script>");
            out.println("alert('비밀번호가 틀립니다.');");
            out.println("history.back();");
            out.println("</script>");
        } else {
            m.setPassword(newpassword);
            this.memberService.changepw(m);

            out.println("<script>");
            out.println("alert('비밀번호가 변경되었습니다.');");
            out.println("location.href='/mypage'");
            out.println("</script>");
        }

        return null;
    }

    @RequestMapping("/del_mem")
    public ModelAndView del_mem() {
        return new ModelAndView("mypage/delmem");
    }

    // user 삭제
    @PostMapping("/del_mem_ok")
    public ModelAndView del_mem_ok(String password, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) session.getAttribute("id");
        UsersVO m = this.memberService.loginCheck(id);

        if (!m.getPassword().equals(password)) {
            out.println("<script>");
            out.println("alert('비밀번호가 틀립니다.');");
            out.println("history.back();");
            out.println("</script>");
        } else {
            this.memberService.del_mem(id);
            session.invalidate();

            out.println("<script>");
            out.println("alert('회원 탈퇴 했습니다.');");
            out.println("location='/';");
            out.println("</script>");
        }

        return null;
    }

    // 프로필 업데이트
    @PostMapping("/saveProfile")
    public void saveProfile(@RequestParam("bio") String bioContent, 
                            @RequestParam("profile_image") MultipartFile profileImage, 
                            HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String userId = (String) session.getAttribute("id");
        
        if (userId == null) {
            response.sendRedirect("/login");
            return;
        }
        
        // 자기소개 저장
        UsersVO user = new UsersVO();
        user.setUser_id(userId);
        user.setBio(bioContent);
        memberService.updatebio(user);

        // 프로필 이미지 저장
        if (!profileImage.isEmpty()) {
            // 업로드 디렉토리 설정
            String uploadDirectory = "/C:/files/"; //이새끼 모름 그냥 뭐임
            Path uploadPath = Paths.get(uploadDirectory);

            // 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 이름 설정 (중복 방지를 위해 UUID 사용)
            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            
            // 파일 저장
            profileImage.transferTo(filePath.toFile());

            // 저장된 파일 경로를 사용자 프로필에 설정
            user.setProfile_image(filePath.toString());
            memberService.updateProfilePicPath(user);
        }

        out.println("<script>");
        out.println("alert('프로필이 저장되었습니다.');");
        out.println("location.href='/mypage';");
        out.println("</script>");
    }
}
