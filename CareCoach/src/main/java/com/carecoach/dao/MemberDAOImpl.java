package com.carecoach.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carecoach.vo.UsersVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlsession;

    @Override
    public int checkUserId(String user_id) {
        return sqlsession.selectOne("m_checkUserId", user_id);
    }

    @Override
    public int checkEmail(String email) {
        return sqlsession.selectOne("m_checkEmail", email);
    }

    @Override
    public void insertUser(UsersVO user) throws Exception {
        System.out.println("MemberDAOImpl: insertUser 시작");
        try {
            sqlsession.insert("m_insertUser", user);
            System.out.println("MemberDAOImpl: SQL 실행 완료");
        } catch (Exception e) {
            System.err.println("MemberDAOImpl: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("MemberDAOImpl: insertUser 종료");
        }
    }


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
        this.sqlsession.update("m_del", id);
    }


    @Override
    public void updatebio(UsersVO bio) {
        this.sqlsession.update("bio_update", bio);
    }

    @Override
    public UsersVO findId(String email) {
        return this.sqlsession.selectOne("m_findId", email);
    }

    @Override
    public UsersVO usersCheck(String email) {
        // TODO Auto-generated method stub
        return this.sqlsession.selectOne("m_findId", email);
    }


    @Override
    public void updateProfilePicPath(UsersVO fileName) {
        // TODO Auto-generated method stub
        this.sqlsession.update("updateProfilePicPath", fileName);

    }


}
