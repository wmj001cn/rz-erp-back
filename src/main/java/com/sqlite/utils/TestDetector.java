/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.mozilla.universalchardet.UniversalDetector
 */
package com.sqlite.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.mozilla.universalchardet.UniversalDetector;

public class TestDetector {
    public static void main(String[] args) throws IOException {
        int nread;
        byte[] buf = new byte[4096];
        FileInputStream fis = new FileInputStream("H:\\ushare\\U123\\share to jay\\2017_4533629001.txt");
        UniversalDetector detector = new UniversalDetector();
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            //System.out.println("Detected encoding = " + encoding);
        } else {
            //System.out.println("No encoding detected.");
        }
        detector.reset();
    }
}

