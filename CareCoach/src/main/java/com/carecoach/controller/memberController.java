package com.carecoach.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carecoach.service.MemberService;
import com.carecoach.vo.UsersVO;

import pwdconv.PwdChange;

@Controller
public class memberController {



	@Autowired
	private MemberService memberService;

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

        if (user == null || !user.getEmail().equals(email)) {
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

		if (m == null || !m.getEmail().equals(email)) {
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



	//마이페이지
	@RequestMapping("/mypage")
	public String mypage() {

		return "mypage/mypage";
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
	
	//회원 탈퇴
	@RequestMapping("/delmem")
	public String delmem() {
		return "mypage/delmem";
	}

	@RequestMapping("/del_mem")
	public ModelAndView del_mem() {

		return new ModelAndView("mypage/delmem");
	}
	@PostMapping("/del_mem_ok")
	public ModelAndView del_mem_ok(String password ,HttpServletResponse response,HttpSession session) throws Exception{
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

			this.memberService.del_mem(id);
			session.invalidate();

			out.println("<script>");
			out.println("alert('회원 탈퇴 했습니다.');");
			out.println("location='/';");
			out.println("</script>");
		}

		return null;
	}

}

