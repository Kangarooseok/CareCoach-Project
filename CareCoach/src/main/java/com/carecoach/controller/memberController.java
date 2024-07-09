package com.carecoach.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

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

@Controller
public class memberController {


    @Autowired
    private MemberService memberService;


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




	
	@GetMapping(value = "/checkIdAvailability", produces = "application/json")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam String user_id) {
        System.out.println("유저 아이디 요청 " + user_id);  
        boolean isAvailable = memberService.isUserIdAvailable(user_id);
        System.out.println("아이디 체크 : " + user_id + ", 사용 가능: " + isAvailable);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("available", isAvailable);
        return response;
    }
	
	//아이디중복확인 페이지
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
            out.println("<script>alert('회원가입이 완료되었습니다.'); </script>");
            return "member/login";
        } catch (DuplicateKeyException e) {
            out.println("<script>alert('" + e.getMessage() + "'); history.back();</script>");
        } catch (Exception e) {
            System.err.println("예외 발생: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            out.println("<script>alert('회원가입 중 오류가 발생했습니다.'); history.back();</script>");
        }
        
        return null;
    }
	
    

	
    //회원가입 페이지
    @GetMapping("/join")
 	public String join() {
 	    return "member/join";
 	}

	//로그인 화면 
	@RequestMapping("/login")
	public ModelAndView login() {

		return new ModelAndView("member/login");
	}


	//아이디 찾기
	@RequestMapping("/findId")
	public String findId() {
		return "member/findId";
	}

	//비밀번호 찾기
	@RequestMapping("/findPwd")
	public String findPwd() {

		return "member/findPwd";
	}

	//비밀번호 찾기 결과 
	@RequestMapping(value = "/findPwdResult", method = RequestMethod.POST)
    public ModelAndView findPwdResult(String user_id, String email, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 사용자 조회 로직
        UsersVO user = this.memberService.findId(email);

        if (user == null || !user.getEmail().equals(email) || !user.getUser_id().equals(user_id)) {
            // 사용자 정보를 찾을 수 없는 경우
            out.println("<script>");
            out.println("alert('해당 이메일과 아이디로 등록된 사용자가 없습니다.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return null;
        } else {
        	session.setAttribute("email",email);
            // 사용자 정보를 찾은 경우 resetMode를 true로 설정하고 JSP로 이동
            ModelAndView mav = new ModelAndView("member/findPwd"); // JSP 페이지 이름을 지정합니다.
            mav.addObject("resetMode", true);
            // 사용자 ID를 재설정 페이지에 전달하여 후속 처리를 쉽게 할 수 있도록 함
            mav.addObject("user_id", user.getUser_id());
            return mav;
        }
    }

	//비밀번호 변경
	@PostMapping("/resetPwd")
	public ModelAndView resetPwd(String newPassword,String confirmPassword,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String email=(String)session.getAttribute("email");
		UsersVO m = this.memberService.userCheck(email);
		

		m.setPassword(confirmPassword);
		this.memberService.changepw(m);

		out.println("<script>");
		out.println("alert('비밀번호가 변경되었습니다.');");
		out.println("location.href='/login'");
		out.println("</script>");

		return null;
	}
	

	//아이디 찾기 결과 
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
			out.println("alert('"+m.getName()+"님의 아이디는 \""+m.getUser_id()+"\"입니다.');");
			out.println("location.href='/login'");
			out.println("</script>");
		}
		return null;
	}




	//로그아웃
	@RequestMapping("/member_logout")
	public ModelAndView member_logout(HttpServletResponse response,
			HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		session.invalidate();//세션 만료 => 로그아웃

		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location='/';");
		out.println("</script>");

		return null;
	}//member_logout()


	public static boolean isLogin(HttpSession session,HttpServletResponse response)
			throws Exception{
		PrintWriter out=response.getWriter();
		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요.');");
			out.println("location='member_login';");
			out.println("</script>");

			return false;
		}
		return true;
	}
	
	@PostMapping("/member_login_ok")
	public ModelAndView member_login_ok(String user_id,String password,
			HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		UsersVO m = this.memberService.loginCheck(user_id);//아이디와 가입회원 1인 경우만
		//로그인 인증 처리한다.


		if(m == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!m.getPassword().equals(password)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다.');");
				out.println("history.go(-1);");
				out.println("</script>");        		
			}else {
				session.setAttribute("id",user_id);//세션 id키이름에 아이디를 저장
				session.setMaxInactiveInterval(1800); // 세션 타임아웃을 30분(1800초)으로 설정
				return new ModelAndView("redirect:/");
			}
		}
		return null;
	}//member_login_ok()
	
	//회원 탈퇴
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
	public ModelAndView chgpw_ok(String password,String newpassword,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String id=(String)session.getAttribute("id");
		UsersVO m = this.memberService.loginCheck(id);

		if(!m.getPassword().equals(password)) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
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
