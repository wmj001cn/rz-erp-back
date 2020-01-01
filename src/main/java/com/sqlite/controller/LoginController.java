package com.sqlite.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @PostMapping("/doLogin")
    public LoginResult doLogin(@RequestBody UsernamePasswordToken token) {
        LoginResult result = new LoginResult();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            result.setSessionId(subject.getSession().getId());;
            System.out.println("登录成功!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            result.setResultCode(1);
            System.out.println("登录失败!");
        }
        
        
        return result;
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @Autowired
    private SimpMessagingTemplate template;
    
    @GetMapping("/login")
    public void  login() {
        send("/topic/aut_failure", new HashMap<>());
    }
    
    private Gson gson = new Gson();
private void send(String dest, Object object) {
        
        String json = this.gson.toJson(object);
        
        //System.out.println(json);
        this.template.convertAndSend(dest, json);
    }
}