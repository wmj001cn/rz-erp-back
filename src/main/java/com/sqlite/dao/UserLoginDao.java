package com.sqlite.dao;

import com.sqlite.entities.UserLogin;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginDao extends CrudRepository<UserLogin, Integer> {
	public UserLogin findUserLoginByUserNameAndPassword(String var1, String var2);
}

