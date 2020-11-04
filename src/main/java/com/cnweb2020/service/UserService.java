/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.model.UserModel;
import com.cnweb2020.service.iService.IUserService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author quyenhaha
 */
public class UserService implements IUserService{

    @Inject
    private IUserDAO userDAO;
    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean findUserByAccount(String userAccount, String password) {
        return userDAO.findUserByAccount(userAccount, password);
    }
    
}
