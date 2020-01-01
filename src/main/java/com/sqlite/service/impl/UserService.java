/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.sqlite.service.impl;

import com.sqlite.dao.UserLoginDao;
import com.sqlite.entities.UserLogin;
import com.sqlite.models.LoginModel;
import com.sqlite.models.UserLoginModel;
import com.sqlite.service.IUserService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
implements IUserService {
    @Autowired
    private UserLoginDao userDao;

    @Override
    public UserLoginModel getUser(LoginModel loginModel) {
        UserLoginModel model = new UserLoginModel();
        UserLogin user = (UserLogin)this.userDao.findOne(Integer.valueOf(1));
        if (user != null) {
            model.setEmail(user.getEmail());
            model.setFirstName(user.getFirstName());
            model.setId(user.getId());
            model.setLastName(user.getLastName());
            model.setMobile(user.getPassword());
            model.setPassword(user.getPassword());
            model.setUserName(user.getUserName());
        }
        return model;
    }
}

