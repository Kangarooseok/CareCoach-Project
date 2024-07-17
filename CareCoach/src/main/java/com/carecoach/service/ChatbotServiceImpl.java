package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecoach.dao.ChatbotDAO;

@Service
public class ChatbotServiceImpl implements ChatbotService {


	@Autowired
    private ChatbotDAO chatbotDAO;

	@Override
	public String GetTheKey(int keyID) {
		
		return this.chatbotDAO.GetTheKey(keyID);
	}

    
}
