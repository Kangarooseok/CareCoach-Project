package com.carecoach.dao;

import com.carecoach.vo.TermsVO;
import java.util.List;

public interface TermsDAO {
	
    TermsVO getTermsById(int id);
    List<TermsVO> getAllTerms();
    void insertTerms(TermsVO terms);
    void updateTerms(TermsVO terms);
    void deleteTerms(int id);
}
