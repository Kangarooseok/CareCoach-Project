package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecoach.dao.MemberDAO;
import com.carecoach.vo.UsersVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberdao;

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

	@Override
	public UsersVO findId(String email) {
		return this.memberdao.findId(email);
	}

	@Override
	public UsersVO userCheck(String email) {
		// TODO Auto-generated method stub
		return this.memberdao.usersCheck(email);
	}



	
	
}
