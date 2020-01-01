package com.sqlite.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;

import org.mozilla.universalchardet.UniversalDetector;

import boot.ApplicationConfig;

public class OrderFileUtils {

    private static int getOrderQuantityByOrderNum(String orderNumber, boolean isMancy) throws IOException {
        String orderFileHome = ApplicationConfig.get("app.zdspool");
        
        String[] files  = OrderFileUtils.getFilesStartWith(orderFileHome, orderNumber);
        if(files == null){
        	return -2;
        }
        
        for (String name :files) {
            File file = new File(orderFileHome + "\\" + name);
            int orderQuantity = OrderFileUtils.parseFile(orderNumber, file, isMancy);
            if (orderQuantity == 0) continue;
            return orderQuantity;
        }
        return -2;
    }

    public static String[] getFilesStartWith(String dirWay, String orderNum) {
        File directory = new File(dirWay);
        String[] listFiles = directory.list();
        return listFiles;
    }

    private static int parseFile(String orderNumber, File file, boolean isMancy) throws IOException {
        HashSet<String> map = new HashSet<String>();
        List<String> lines = OrderFileUtils.readLines(file);
        for (int i = lines.size() - 1; i >= 0; --i) {
            boolean isEndingLine;
            String line = lines.get(i);
            
            if (line.trim().isEmpty()) continue;
            
            int indexOf = line.indexOf(orderNumber);
            boolean match = indexOf > -1;
            isEndingLine = !line.startsWith("..");
            
            if (!match) continue;
            
            if (isEndingLine) break;
            
            int start = indexOf + orderNumber.length();
            String sku = line.substring(start, start + 3);
            
            if (isMancy) {
                String[] splits = line.split("\\s+");
                String lastSection = splits[splits.length - 1];
                String lastTwo = lastSection.substring(0, 2);
                //System.out.println(lastTwo);
                map.add(lastTwo);
                continue;
            }
            
            map.add(sku);
        }
        
        if (isMancy && map.size() == 1 && map.contains("AA")) {
            return -1;
        }
        
        return map.size();
    }

    private static List<String> readLines(File file) throws IOException {
        String charsetDetected = UniversalDetector.detectCharset((File)file);
        if (charsetDetected == null) {
            charsetDetected = "UTF-8";
        }
        return Files.readAllLines(file.toPath(), Charset.forName(charsetDetected));
    }

    public static int getSkuCountByOrderNum(String orderNum, int orderType) {
        int orderQuantity;
        boolean isMancy = orderType == 1;
        try {
            orderQuantity = OrderFileUtils.getOrderQuantityByOrderNum(orderNum, isMancy);
        }
        catch (IOException e) {
            throw new RuntimeException("Something wrong with IO", e);
        }
        int skuCount = orderQuantity;
        return skuCount;
    }
}

