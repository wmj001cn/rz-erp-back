/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.impinj.octane.OctaneSdkException
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 
package com.sqlite.model;

import boot.Pass;
import boot.PassFactory;
import com.impinj.octane.OctaneSdkException;
import com.sqlite.domain.BasicTagInfoConsumer;
import com.sqlite.messaging.MessageManager;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import model.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mock {
    @Autowired
    private MessageManager mm;
    @Autowired
    private BasicTagInfoConsumer ctic;

    public static void main(String[] args) {
        Long d = Long.parseLong("483002BB", 16) - Long.parseLong("483002B9", 16);
        //System.out.println(d == 1);
    }

    public void doIt() throws OctaneSdkException {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        passs.addAll(this.createLeadingPass("E342234789ABCDEF00", 1));
        passs.addAll(this.createOnePass("3034329f40229432210006d"));
        passs.addAll(this.createOnePass("3034329f40229432210006f"));
        passs.addAll(this.createUnEncodePass());
        //passs.addAll(this.createOnePass("3034329f402294322100070"));
        //passs.addAll(this.createLeadingPass("", 1, "3034329f402294322100070"));
        passs.addAll(this.createOnePass("3038C359406499C005100007"));
        passs.addAll(this.createOnePass("3038C359406499C0051000F8"));
        passs.addAll(this.createOnePass("303960ffc0dee18085100424"));
        passs.addAll(this.createOnePass("303960ffc0dee18085100423"));
        passs.addAll(this.createOnePass("303960ffc0dee18085100422"));
        passs.addAll(this.createOnePass("303960ffc0dee18085100425"));
        passs.addAll(this.createOnePass("6014329f402294322100070"));
        passs.addAll(this.createOnePass("4034329f402294322100081"));
        passs.addAll(this.createOnePass("4034329f402294322100082"));
        passs.addAll(this.createOnePass("4034329f402294322100083"));
        passs.addAll(this.createOnePass("4034329f402294322100084"));
        passs.addAll(this.createOnePass("4034329f402294322100084"));
        passs.addAll(this.createOnePass("4034329f402294322100084"));
        passs.addAll(this.createOnePass("4034329f402294322100085"));
        passs.addAll(this.createOnePass("4034329f402294322100086"));
        passs.addAll(this.createOnePass("4034329f402294322100083"));
        passs.addAll(this.createOnePass("4034329f402294322100088"));
        passs.addAll(this.createOnePass("4034329f402294322100089"));
        
        passs.addAll(this.createOnePass("3034329f402294322100071"));
        passs.addAll(this.createOnePass("3034329f402294322100072"));
        passs.addAll(this.createOnePass("3034329f402294322100073"));
        
        
        passs.addAll(this.createOnePass("6014329f402294322100070"));
        passs.addAll(this.createOnePass("6014329f402294322100070"));
        passs.addAll(this.createDeadPass("", 1));
        passs.addAll(this.createOnePass("3034329f402294322100078"));
        passs.addAll(this.createOnePass("3034329f402294322100078"));
        passs.addAll(this.createDeadPass("", 1));
        
        passs.addAll(this.createOnePass("6014329f402294322100070"));
        passs.addAll(this.createDeadPass("", 1));
        passs.addAll(this.createDeadPass("", 1));
        
        
        passs.addAll(this.createOnePass("4034329f402294322100084"));
        passs.addAll(this.createOnePass("4034329f402294322100085"));
        
        passs.addAll(this.createOnePass("3034329f402294322100001"));
        
        passs.addAll(this.createListSkuPassFrom("123456789ABCDEF0", 4, 9));
        passs.addAll(this.createMultipleReadPass("323456789ABCDEF00", 3));
        passs.addAll(this.createDeadPass("", 1));
        passs.addAll(this.createListSkuPass("223456789ABCDEF00", 3, -2));
        for (int j = 0; j < passs.size(); ++j) {
            Pass pass = (Pass)passs.get(j);
            this.ctic.onPass(pass);
        }
    }

    private List<Pass> createListRange(String skuHeader) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        HashSet<TagInfo> tag = new HashSet<TagInfo>();
        TagInfo tag2 = new TagInfo();
        tag2.setEpc(skuHeader);
        tag2.setTid("" + System.currentTimeMillis() + "t");
        tag.add(tag2);
        Pass pass = PassFactory.createPass2(tag, false);
        passs.add(pass);
        return passs;
    }

    private List<Pass> createListSkuPassFrom(String skuHeader, int pieces, int n) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        for (int i = n; i < pieces + n; ++i) {
            HashSet<TagInfo> tag = new HashSet<TagInfo>();
            TagInfo tag2 = new TagInfo();
            tag2.setEpc(skuHeader + (i + 1));
            tag2.setTid("" + System.currentTimeMillis() + "t");
            tag.add(tag2);
            Pass pass = PassFactory.createPass2(tag, false);
            passs.add(pass);
        }
        return passs;
    }

    private List<Pass> createListSkuPass(String skuHeader, int pieces, int n) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        for (int i = 0; i < pieces; ++i) {
            HashSet<TagInfo> tag = new HashSet<TagInfo>();
            TagInfo tag2 = new TagInfo();
            tag2.setEpc(skuHeader + (i + 1));
            tag2.setTid("" + System.currentTimeMillis() + "t");
            if (n >= 0 && i == n) {
                tag2.setEpc(UUID.randomUUID().toString().substring(0, 19));
            }
            tag.add(tag2);
            Pass pass = PassFactory.createPass2(tag, false);
            passs.add(pass);
        }
        return passs;
    }

    private List<Pass> createLeadingPass(String skuHeader, int pieces, String epc) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        for (int i = 0; i < pieces; ++i) {
            HashSet<TagInfo> tag = new HashSet<TagInfo>();
            TagInfo tag2 = new TagInfo();
            tag2.setEpc(epc);
            tag2.setTid("" + System.currentTimeMillis() + "t");
            tag.add(tag2);
            Pass pass = PassFactory.createPass2(tag, true);
            passs.add(pass);
        }
        return passs;
    }

    private List<Pass> createOnePass(String epc, String... tid) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        TagInfo tag2 = new TagInfo();
        tag2.setEpc(epc);
        tag2.setTid(tid.length>0?tid[0]:"" + System.currentTimeMillis() + "t");
        HashSet<TagInfo> tag = new HashSet<TagInfo>();
        tag.add(tag2);
        Pass pass = PassFactory.createPass2(tag, false);
        passs.add(pass);
        return passs;
    }
    
    private List<Pass> createUnEncodePass() {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        TagInfo tag2 = new TagInfo();
        String tid = "E280329f40229432210" + String.valueOf(System.currentTimeMillis()).substring(0, 4) + "";
        tag2.setEpc(tid);
		tag2.setTid(tid);
        HashSet<TagInfo> tag = new HashSet<TagInfo>();
        tag.add(tag2);
        Pass pass = PassFactory.createPass2(tag, false);
        passs.add(pass);
        return passs;
    }

    private List<Pass> createDeadPass(String skuHeader, int pieces) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        passs.add(PassFactory.createPass(null, false));
        return passs;
    }

    private List<Pass> createMultipleReadPass(String skuHeader, int pieces) {
        ArrayList<Pass> passs = new ArrayList<Pass>();
        HashSet<TagInfo> tag = new HashSet<TagInfo>();
        for (int i = 0; i < pieces; ++i) {
            TagInfo tag2 = new TagInfo();
            tag2.setEpc(skuHeader + (i + 1));
            tag2.setTid("11234" + i);
            tag.add(tag2);
        }
        Pass pass = PassFactory.createPass2(tag, false);
        passs.add(pass);
        return passs;
    }
}

*/