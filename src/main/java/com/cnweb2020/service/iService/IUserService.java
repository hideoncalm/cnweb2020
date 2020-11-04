/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.service.iService;

import com.cnweb2020.model.UserModel;
import java.util.List;

/**
 *
 * @author quyenhaha
 */
public interface IUserService {
    List<UserModel> findAll();
    boolean findUserByAccount(String userAccount, String password);
}
