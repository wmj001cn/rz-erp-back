/*
 * Decompiled with CFR 0_123.
 */
package model;

public class TagInfo {
    private String tid = "";
    private String epc = "";
    private String chipType;
    
    static {
    	System.out.println("dong now");
    }

    public String getChipType() {
        return this.chipType;
    }

    public void setChipType(String chipType) {
        this.chipType = chipType;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getEpc() {
        return this.epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.epc == null ? 0 : this.epc.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        TagInfo other = (TagInfo)obj;
        if (this.epc == null ? other.epc != null : !this.epc.equals(other.epc)) {
            return false;
        }
        return true;
    }
}

