package com.sqlite.controller;

import java.io.Serializable;

public class LoginResult {
    private int resultCode;
    
    private  Serializable sessionId;
   

    public Serializable getSessionId() {
        return sessionId;
    }

    public void setSessionId(Serializable sessionId) {
        this.sessionId = sessionId;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
