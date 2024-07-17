package com.carecoach.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatbotDAOImpl implements ChatbotDAO {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public String GetTheKey(int keyID) {

		return this.sqlsession.selectOne("secret_key", keyID);
	}

}
