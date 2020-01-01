/*package com.sqlite.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruu.QRCode;
import com.ruu.Sample;

@RestController
public class TestController {
	public static Map<String, String> mp = new HashMap<>();
	
	static {
		mp.put("000000000000000022000001", "1");
	}
	
    @RequestMapping(value={"/activate"}, produces={"application/json"})
    @ResponseBody
    public String activate(String tid) throws Exception {
        
    	if(mp.containsKey(tid)){
    		//System.out.println("ah, this is the ink from my company, let me activate it for you");
    		
    		
    		String qrstring = Sample.getBase64(tid);
    		mp.put(tid, qrstring);
    		
    		return "SUCCESS";
    		
    		
    		
    	} else {
    		//System.out.println("ah, this is not the ink from my company, not going to activate it!");
    		return "FAILED";
    	}
    }
    
    @RequestMapping(value={"/qr"}, produces={"application/json"})
    @ResponseBody
    public byte[] qr(String tid) throws Exception {
    
    	String string = mp.get(tid);
    	
    	
		return QRCode.getQrBytes(string);
    }

}

*/