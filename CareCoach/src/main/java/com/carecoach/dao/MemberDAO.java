package com.carecoach.dao;

import com.carecoach.vo.UsersVO;

public interface MemberDAO {

	UsersVO loginCheck(String user_id);
	UsersVO usersCheck(String email);
	UsersVO findId(String email);
	void changepw(UsersVO m);
	void del_mem(String id);
<<<<<<< HEAD

	void updatebio(UsersVO bio);
	void updateProfilePicPath(UsersVO user);
=======
	void updatebio(UsersVO bio);
	void updateProfilePicPath(UsersVO fileName);
>>>>>>> dev/devlogintest
	

    int checkUserId(String user_id);
    int checkEmail(String email);
    void insertUser(UsersVO user) throws Exception;

}
