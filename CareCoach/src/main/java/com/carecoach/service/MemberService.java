package com.carecoach.service;

import com.carecoach.vo.UsersVO;

public interface MemberService {

	UsersVO loginCheck(String user_id);

	void changepw(UsersVO m);

	void del_mem(String id);

	boolean isUserIdAvailable(String user_id);
    void registerUser(UsersVO user) throws Exception;

}
