/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.mapper;

import com.cnweb2020.model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyenhaha
 */
public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try {
            UserModel userModel = new UserModel();
            userModel.setId(resultSet.getInt("id"));
            userModel.setFullName(resultSet.getString("fullName"));
            userModel.setAccount(resultSet.getString("account"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setAddress(resultSet.getString("address"));
            userModel.setEmail(resultSet.getString("email"));
            userModel.setPhone(resultSet.getString("phone"));
            return userModel;
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
