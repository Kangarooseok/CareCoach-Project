package com.carecoach.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carecoach.vo.UsersVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession sqlsession;

	@Override
	public UsersVO loginCheck(String user_id) {
		return this.sqlsession.selectOne("m_loginCheck", user_id);
	}

	@Override
	public void changepw(UsersVO m) {
		this.sqlsession.update("m_update", m);
		
	}

	@Override
	public void del_mem(String id) {
		this.sqlsession.update("m_del",id);
	}

<<<<<<< HEAD
    @Override
    public void updatebio(UsersVO bio) {
        this.sqlsession.update("bio_update", bio);
    }
=======
	@Override
	public UsersVO findId(String email) {
		return this.sqlsession.selectOne("m_findId", email);
	}

	@Override
	public UsersVO usersCheck(String email) {
		// TODO Auto-generated method stub
		return this.sqlsession.selectOne("m_findId", email);
	}

	
	
>>>>>>> dev/devlogin

	@Override
    public void updateProfilePicPath(UsersVO user) {
        this.sqlsession.update("updateProfilePicPath", user);
    }
	
}
