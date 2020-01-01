package com.sqlite.mapper;

import javax.persistence.OneToMany;

public class City {
    
    @OneToMany
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
