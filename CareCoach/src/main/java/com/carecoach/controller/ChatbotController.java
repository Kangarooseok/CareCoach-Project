package com.carecoach.controller;

import com.carecoach.service.ChatbotService;
import com.carecoach.service.PostService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONObject;
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
        String response = processMessage(message); // 수신된 메세지 처리
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response); // 챗봇 서비스에서 반한된 json 응답을 반환
    }

    public String processMessage(String voiceMessage) {
        System.out.println("test0");
        return main(voiceMessage);
    }

    private String main(String voiceMessage) {

        String secret_key = chatbotService.GetTheKey(1);
        String api_url = chatbotService.GetTheKey(2);

        System.out.println(secret_key);
        System.out.println(api_url);

        String chatbotMessage = "";
        try {
            System.out.println("test1");
            URL url = new URL(api_url);
            System.out.println("test2");

            String message = getReqMessage(voiceMessage);

            System.out.println("##" + message);

            String encodeBase64String = makeSignature(message, secret_key);

            // HTTP 연결 설정
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;UTF-8");
            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(message.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();

            if(responseCode==200) { // 성공적으로 응답을 받았을 때
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                chatbotMessage = response.toString();

                // JSON 파싱 및 description 추출
                try {
                    JSONObject jsonResponse = new JSONObject(chatbotMessage);
                    JSONArray bubbles = jsonResponse.getJSONArray("bubbles");
                    if (bubbles.length() > 0) {
                        JSONObject firstBubble = bubbles.getJSONObject(0);
                        String description = firstBubble.getJSONObject("data").getString("description");

                        // description만 Base64로 인코딩
                        String encodedDescription = Base64.getEncoder().encodeToString(description.getBytes("UTF-8"));
                        firstBubble.getJSONObject("data").put("description", encodedDescription);
                    }
                    chatbotMessage = jsonResponse.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("서버 응답: " + chatbotMessage);
            } else { // 오류가 발생했을 때
                chatbotMessage = con.getResponseMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            chatbotMessage = "Error: " + e.getMessage(); // 오류 메세지 반환
        }
        return chatbotMessage; // 챗봇응답을 리턴
    }

    private String makeSignature(String message, String secretKey) {
        String encodeBase64String = "";
        try {
            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e){
            System.out.println(e);
        }
        return encodeBase64String;
    }

    private String getReqMessage(String voiceMessage) {
        String requestBody = "";
        try {
            JSONObject obj = new JSONObject();
            long timestamp = new Date().getTime();

            System.out.println("##"+timestamp);

            obj.put("version", "v2");
            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();
            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", new String(voiceMessage.getBytes("UTF-8"), "UTF-8"));
            data_obj.put("description", voiceMessage);
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.put(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            obj.put("event", "send");

            requestBody = obj.toString();
        } catch (Exception e){
            System.out.println("## Exception : " + e);
        }
        return requestBody;
    }
}