package com.carecoach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.carecoach.dao.MemberDAO;
import com.carecoach.vo.UsersVO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberdao;

    private static final int SALT_LENGTH = 16;

    @Override
    public boolean isUserIdAvailable(String userId) {
        return memberdao.checkUserId(userId) == 0;
    }

    @Override
    public void registerUser(UsersVO user) throws Exception {
        if (!isValidUserId(user.getUserId())) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        
        try {
            if (memberdao.checkUserId(user.getUserId()) > 0) {
                throw new DuplicateKeyException("이미 존재하는 아이디입니다.");
            }
            if (memberdao.checkEmail(user.getEmail()) > 0) {
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }

            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            memberdao.insertUser(user);
            System.out.println("MemberServiceImpl: 사용자 DB 삽입 완료");
            System.out.println("MemberServiceImpl: 모든 작업 완료");
        } catch (DuplicateKeyException e) {
            System.err.println("MemberServiceImpl: 중복 데이터 예외 - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("MemberServiceImpl: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("MemberServiceImpl: registerUser 종료");
        }
    }

    @Override
    public String hashPassword(String password) throws NoSuchAlgorithmException {
    	byte[] salt = generateSalt();
        byte[] hashedPassword = hashWithSalt(password, salt);
        
        byte[] saltAndHash = new byte[salt.length + hashedPassword.length];
        System.arraycopy(salt, 0, saltAndHash, 0, salt.length);
        System.arraycopy(hashedPassword, 0, saltAndHash, salt.length, hashedPassword.length);
        
        String result = Base64.getEncoder().encodeToString(saltAndHash);
        System.out.println("New hashed password: " + result);  // 디버깅을 위한 로그
        return result;
    }

    @Override
    public boolean verifyPassword(String inputPassword, String storedPassword) throws NoSuchAlgorithmException {
    	try {
            byte[] saltAndHash = Base64.getDecoder().decode(storedPassword);
            byte[] salt = new byte[SALT_LENGTH];
            byte[] storedHashBytes = new byte[saltAndHash.length - SALT_LENGTH];
            
            System.arraycopy(saltAndHash, 0, salt, 0, SALT_LENGTH);
            System.arraycopy(saltAndHash, SALT_LENGTH, storedHashBytes, 0, storedHashBytes.length);
            
            byte[] computedHash = hashWithSalt(inputPassword, salt);
            
            System.out.println("Stored hash: " + Base64.getEncoder().encodeToString(storedHashBytes));
            System.out.println("Computed hash: " + Base64.getEncoder().encodeToString(computedHash));
            System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
            
            return MessageDigest.isEqual(computedHash, storedHashBytes);
        } catch (IllegalArgumentException e) {
        
            return storedPassword.equals(oldHashPassword(inputPassword));
        }
    }
    
    private byte[] hashWithSalt(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        md.update(password.getBytes());
        return md.digest();
    }

    private String oldHashPassword(String password) throws NoSuchAlgorithmException {
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] hashedPassword = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private boolean isValidUserId(String userId) {
        return userId != null && userId.matches("^[a-zA-Z0-9]{6,12}$");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6 && password.length() <= 12;
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    @Override
    public UsersVO loginCheck(String userId) {
        return this.memberdao.loginCheck(userId);
    }

    @Override
    public void changepw(UsersVO m) {
    	try {
            String hashedPassword = hashPassword(m.getPassword());
            m.setPassword(hashedPassword);
            this.memberdao.changepw(m);
            System.out.println("Password changed in service. New hash: " + hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("비밀번호 해싱 중 오류 발생", e);
        }
    }

    @Override
    public void del_mem(String id) {
        this.memberdao.del_mem(id);
    }

    @Override
    public UsersVO findId(String email) {
        return this.memberdao.findId(email);
    }

    @Override
    public UsersVO userCheck(String email) {
        return this.memberdao.usersCheck(email);
    }

    @Override
    public void updatebio(UsersVO bio) {
        this.memberdao.updatebio(bio);
    }

    @Override
    public void updateProfilePicPath(UsersVO fileName) {
        this.memberdao.updateProfilePicPath(fileName);
    }
}