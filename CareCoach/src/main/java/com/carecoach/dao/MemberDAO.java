package com.carecoach.dao;

import com.carecoach.vo.UsersVO;

public interface MemberDAO {

	UsersVO loginCheck(String user_id);
	void changepw(UsersVO m);
	void del_mem(String id);

	

}
