package com.ruiziot.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruiziot.test.base.DaoTestBase;
import com.sqlite.mapper.City;
import com.sqlite.mapper.CityMapper;

public class MybatisTest extends DaoTestBase{
    
    @Autowired
    private CityMapper cityMapper;
    
    @org.junit.Test
    public void test(){
        City city = cityMapper.findByState("usa");
        System.out.println(city);
    }
    
}
