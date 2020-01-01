/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.messaging.simp.SimpMessagingTemplate
 *  org.springframework.stereotype.Component
 */
package com.sqlite.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import boot.Pass;

import com.google.gson.Gson;
import com.sqlite.license.model.LicenseFailureMessage;
import com.sqlite.model.InspectResult;

@Component
public class MessageManager {
    @Autowired
    private SimpMessagingTemplate template;
    private Gson gson = new Gson();

    public void sendPass(Pass pass) {
        String json = this.gson.toJson(pass);
        //System.out.println("pass..." + json);
		this.template.convertAndSend("/topic/greetings", json);
    }

    public void sendAdd(String epc, String tid, String status) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("epc", epc);
        map.put("tid", tid);
        map.put("status", status);
        this.template.convertAndSend("/topic/add", this.gson.toJson(map));
    }

    public void sendReadEPC(String epc, String tid) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("epc", epc);
        map.put("tid", tid);
        this.template.convertAndSend("/topic/input", this.gson.toJson(map));
    }

    public void sendGpiChange() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("time", String.valueOf(System.currentTimeMillis()));
        this.template.convertAndSend("/topic/detected", this.gson.toJson(map));
    }

    public void sendProdeTrueSignal(long currentTimeMillis) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("signal", "true");
        map.put("time", String.valueOf(currentTimeMillis));
        this.template.convertAndSend("/topic/prode", this.gson.toJson(map));
    }

    public void sendProdeFalseSignal(long currentTimeMillis) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("signal", "false");
        map.put("time", String.valueOf(currentTimeMillis));
        this.template.convertAndSend("/topic/prode", this.gson.toJson(map));
    }

    public void sendPrinterStopSignal() {
        HashMap map = new HashMap();
        this.template.convertAndSend("/topic/printStop", this.gson.toJson(map));
    }

    public void sendReenterEPC(Map<String, Object> map) {
        this.template.convertAndSend("/topic/reenter", this.gson.toJson(map));
    }

    public void sendLicenseFailureMessage(LicenseFailureMessage licenseFailureMessage) {
    }

	public void sendInspectResult(String queue, InspectResult inspectRslt) {
		  this.template.convertAndSend(queue, this.gson.toJson(inspectRslt));
	}
}

