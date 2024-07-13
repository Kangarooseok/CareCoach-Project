package com.carecoach.dao;

import com.carecoach.vo.UsersVO;

public interface MemberDAO {

    UsersVO loginCheck(String userId);

    UsersVO usersCheck(String email);

    UsersVO findId(String email);

    void changepw(UsersVO m);

    void del_mem(String id);

    void updatebio(UsersVO bio);

    void updateProfilePicPath(UsersVO fileName);


    int checkUserId(String userId);

    int checkEmail(String email);

    void insertUser(UsersVO user) throws Exception;

}
