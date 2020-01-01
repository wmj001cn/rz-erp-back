package com.sqlite.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {
	
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
    	
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS pass_inspects_" + dateStr + "(" + "seq INTEGER, " + "is_leading bit, " + "tid varchar(130)," + "epc varchar(130)," + "epc_multiple varchar(255)," + "time varchar(130), " + "status varchar(130)," + "order_num varchar(130))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS [order_stat] (id INTEGER, duplicate_count INTEGER, leading_count INTEGER, pieces_count INTEGER, read_multiple_count INTEGER, sku_count INTEGER, good_count INTEGER, supply_count INTEGER, un_encoded_count INTEGER, unreadable_count INTEGER, wrong_count INTEGER, code_type varchar(130),order_num varchar(130))");
            
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

