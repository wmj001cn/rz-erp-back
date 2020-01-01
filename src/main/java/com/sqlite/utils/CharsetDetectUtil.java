package com.sqlite.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.mozilla.universalchardet.UniversalDetector;

public class CharsetDetectUtil {
	
    public static String detect(byte[] content) {
    	
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(content, 0, content.length);
        detector.dataEnd();
        
        return detector.getDetectedCharset();
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(new File("H:\\ushare\\U123\\share to jay\\test.txt").toPath());
        //System.out.println(CharsetDetectUtil.detect(bytes));
    }
    
}

