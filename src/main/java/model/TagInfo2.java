/*
 * Decompiled with CFR 0_123.
 */
package model;

public class TagInfo2 {
    private String tid = "";
    private String epc = "";

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
        TagInfo2 other = (TagInfo2)obj;
        if (!this.tid.equals(other.tid) && this.epc.equals(other.epc)) {
            return true;
        }
        return false;
    }
}

