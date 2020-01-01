/*package com.sqlite.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.InspectContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.ApplicationConfig;

import com.google.gson.Gson;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.ReaderManager;
import com.sqlite.dao.PassInspectDao;
import com.sqlite.domain.ContextManager;
import com.sqlite.domain.OIC;
import com.sqlite.messaging.MessageManager;
import com.sqlite.model.Mock;
import com.sqlite.utils.ReaderProxy;

//@RestController
public class GreetingController2 {
	
    @Autowired
    private ReaderProxy scannerMain;
    @Autowired
    private MessageManager messageManager;
    @Autowired
    private Mock mock;
    @Autowired
    private PassInspectDao passInspectDao;

    @RequestMapping(value="/appendOrder", method=RequestMethod.POST)
    @ResponseBody
    public String appendOrder(@RequestBody InspectContext inspectContext) {
        ContextManager.addOrders(inspectContext.getOrders());
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("resl", "ok");
        return new Gson().toJson(map);
    }

    @RequestMapping(value="/startInspection", method=RequestMethod.POST)
    @ResponseBody
    public String startInspection(@RequestBody InspectContext inspectContext) throws OctaneSdkException, FileNotFoundException, IOException {
        OIC.reset();
        OIC.hasStarted = true;
        ApplicationConfig.load(inspectContext.getConfigName() + ".properties");
        ContextManager.addOrders(inspectContext.getOrders());
        ContextManager.init();
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    GreetingController2.this.mock.doIt();
                }
                catch (OctaneSdkException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("resl", "ok");
        return new Gson().toJson(map);
    }

    @RequestMapping(value={"/stopInspection"})
    @ResponseBody
    public String stopInspection() throws OctaneSdkException {
        if (ContextManager.currentOrder != null) {
            ContextManager.saveOrder(ContextManager.currentOrder);
        }
        OIC.reset();
        OIC.hasStarted = false;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("resl", "ok");
        return new Gson().toJson(map);
    }

    @RequestMapping(value={"/startAutomaticReading"})
    @ResponseBody
    public String startAutomaticReading(String receiveQueueName, @RequestBody Map<String, Object> params) {
        if ("input".equals(receiveQueueName)) {
            for (int i = 0; i < 3; ++i) {
                this.messageManager.sendReadEPC("epc" + i, "tid" + i);
            }
        }
        if ("reenter".equals(receiveQueueName)) {
            ArrayList<String> tagInfos = new ArrayList<String>();
            for (int i = 0; i < 3; ++i) {
                tagInfos.add("epc" + i + ",tid" + i);
            }
            this.messageManager.sendReenterEPC(tagInfos);
        }
        
        if ("add".equals(receiveQueueName)) {
        	
            this.messageManager.sendAdd("3034329f402294322100070", "3034329f402294322100074", "GOOD" + (firstTime?"":"_NOT"));
        }
        
        
        String resl = "ok";
        try {
            this.scannerMain.startRead(receiveQueueName, true);
        }
        catch (OctaneSdkException e) {
            e.printStackTrace();
            resl = "failed";
        }
        return resl;
    	
    	String resl = "ok";
        try {
        	
            scannerMain.startRead(receiveQueueName, params);
        }
        catch (OctaneSdkException e) {
            e.printStackTrace();
            resl = "failed";
        }
        return resl;
    }

    @RequestMapping(value={"/stopAutomaticReading"})
    @ResponseBody
    public String stopAutomaticReading() {
        String resl = "ok";
        return resl;
    }

    @MessageMapping(value={"/connect"})
    @SendTo(value={"/topic/error"})
    public Map<String, String> connectReader() throws Exception {
        ReaderManager.getReader();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("result", "ok");
        return hashMap;
    }

    @RequestMapping(value={"/updateEmptyRead"}, method={RequestMethod.POST})
    @ResponseBody
    public String updateEmptyRead(@RequestParam String epc, @RequestParam String tid, int rowId) {
        String resl = "failed";
        int updatedRowCount = this.passInspectDao.updateEmptyRead(rowId, epc, tid);
        if (updatedRowCount == 1) {
            resl = "ok";
            try {
                ReaderManager.stop();
                this.scannerMain.listenGpiEvent();
            }
            catch (OctaneSdkException e) {
                e.printStackTrace();
            }
        }
        return resl;
    }

}

*/