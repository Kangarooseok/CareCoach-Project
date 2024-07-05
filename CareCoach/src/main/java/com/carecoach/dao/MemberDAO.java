package com.carecoach.dao;

import com.carecoach.vo.UsersVO;

public interface MemberDAO {

	UsersVO loginCheck(String user_id);
	void changepw(UsersVO m);
	void del_mem(String id);
    int checkUserId(String user_id);
    int checkEmail(String email);
    void insertUser(UsersVO user) throws Exception;

}
