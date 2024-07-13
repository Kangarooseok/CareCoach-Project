package com.carecoach.controller;

import com.carecoach.service.ChatbotService;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @GetMapping("/chatbot") // get방식으로 요청
    public String chatbot() {
        return "chatbot/chatbot"; //chatbot.jsp 반환
    }

    @PostMapping(value = "/chatbot/send", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        String response = chatbotService.processMessage(message); // 수신된 메세지 처리
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response); // 챗봇 서비스에서 반한된 json 응답을 반환
    }
}