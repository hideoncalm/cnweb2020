/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.UserModel;
import java.util.List;

public interface IUserDAO extends IGenericDAO<UserModel>{
    List<UserModel> findAll();
    boolean findUserByAccount(String userAccount, String password);
}
