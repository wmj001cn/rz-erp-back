/*
 * Decompiled with CFR 0_123.
 */
package model;

import com.sqlite.model.Order;
import java.util.List;

public class InspectContext {
    private String configName;
    private List<Order> orders;

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

