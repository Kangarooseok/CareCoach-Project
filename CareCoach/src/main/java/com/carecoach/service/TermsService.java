package com.carecoach.service;

import com.carecoach.vo.TermsVO;
import java.util.List;

public interface TermsService {

    TermsVO getTermsById(int id);
    List<TermsVO> getAllTerms();
    void addTerms(TermsVO terms);
    void updateTerms(TermsVO terms);
    void deleteTerms(int id);
}
