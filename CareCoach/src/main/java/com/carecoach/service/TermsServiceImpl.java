package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carecoach.dao.TermsDAO;
import com.carecoach.vo.TermsVO;
import java.util.List;

@Service
public class TermsServiceImpl implements TermsService {

    @Autowired
    private TermsDAO termsDAO;

    @Override
    public TermsVO getTermsById(int id) {
        return termsDAO.getTermsById(id);
    }

    @Override
    public List<TermsVO> getAllTerms() {
        return termsDAO.getAllTerms();
    }

    @Override
    public void addTerms(TermsVO terms) {
        termsDAO.insertTerms(terms);
    }

    @Override
    public void updateTerms(TermsVO terms) {
        termsDAO.updateTerms(terms);
    }

    @Override
    public void deleteTerms(int id) {
        termsDAO.deleteTerms(id);
    }
}