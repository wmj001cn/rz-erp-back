/*
 * Decompiled with CFR 0_123.
 */
package com.sqlite.model;

import com.sqlite.model.SKUProduction;
import java.util.HashSet;
import java.util.Set;

public class MyOrder {
    private Set<SKUProduction> skuProds = new HashSet<SKUProduction>();

    public Set<SKUProduction> getSkuProds() {
        return this.skuProds;
    }

    public void setSkuProds(Set<SKUProduction> skuProds) {
        this.skuProds = skuProds;
    }
}

