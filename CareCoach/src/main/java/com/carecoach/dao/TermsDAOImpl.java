package com.carecoach.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.carecoach.vo.TermsVO;
import java.util.List;


@Repository
public class TermsDAOImpl implements TermsDAO {

		@Autowired
	    private SqlSession sqlSession;

	    @Override
	    public TermsVO getTermsById(int id) {
	        return sqlSession.selectOne("terms.getTermsById", id);
	    }

	    @Override
	    public List<TermsVO> getAllTerms() {
	        return sqlSession.selectList("terms.getAllTerms");
	    }

	    @Override
	    public void insertTerms(TermsVO terms) {
	        sqlSession.insert("terms.insertTerms", terms);
	    }

	    @Override
	    public void updateTerms(TermsVO terms) {
	        sqlSession.update("terms.updateTerms", terms);
	    }

	    @Override
	    public void deleteTerms(int id) {
	        sqlSession.delete("terms.deleteTerms", id);
	    }
	
}
