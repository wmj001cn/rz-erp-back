/*
 * Decompiled with CFR 0_123.
 */
package model;

public class EPCDecodedResource {
    String version;
    EPC epc;
    GS1 gs1;
    Tag tag;

    static class Tag {
        String tagUri;
        String epcHex;
        String epcTagScheme;
        String rawUri;

        Tag() {
        }
    }

    static class GS1 {
        String elementString;
        String gtin;
        String gtinSerial;
        String humanReadable;

        GS1() {
        }
    }

    static class EPC {
        String[] epcComponents;
        String epcUri;
        String epcScheme;

        EPC() {
        }
    }

}

