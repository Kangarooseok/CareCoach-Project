package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carecoach.dao.MemberDAO;
import com.carecoach.vo.UsersVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberdao;

	@Override
	public boolean isUserIdAvailable(String user_id) {
	    return memberdao.checkUserId(user_id) == 0;
	}

    @Override
    public void registerUser(UsersVO user) throws Exception {
        // 서버 측 유효성 검사
        if (!isValidUserId(user.getUser_id())) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        
        try {
            // 중복 체크
            if (memberdao.checkUserId(user.getUser_id()) > 0) {
                throw new DuplicateKeyException("이미 존재하는 아이디입니다.");
            }
            if (memberdao.checkEmail(user.getEmail()) > 0) {
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }

            memberdao.insertUser(user);
            System.out.println("MemberServiceImpl: 사용자 DB 삽입 완료");
            System.out.println("MemberServiceImpl: 모든 작업 완료");
        } catch (DuplicateKeyException e) {
            System.err.println("MemberServiceImpl: 중복 데이터 예외 - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("MemberServiceImpl: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("MemberServiceImpl: registerUser 종료");
        }
     }
    private boolean isValidUserId(String userId) {
        return userId != null && userId.matches("^[a-zA-Z0-9]{6,12}$");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6 && password.length() <= 12;
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
	@Override
	public UsersVO loginCheck(String user_id) {
		return this.memberdao.loginCheck(user_id);
	}

	@Override
	public void changepw(UsersVO m) {
		this.memberdao.changepw(m);
		
	}

	@Override
	public void del_mem(String id) {
		this.memberdao.del_mem(id);
	}

	public UsersVO findId(String email) {
		return this.memberdao.findId(email);
	}

	@Override
	public UsersVO userCheck(String email) {
		// TODO Auto-generated method stub
		return this.memberdao.usersCheck(email);
	}

	@Override
    public void updatebio(UsersVO bio) {
        this.memberdao.updatebio(bio);
    }

    @Override
    public void updateProfilePicPath(UsersVO fileName) {
        this.memberdao.updateProfilePicPath(fileName);
    }

		
	
}
