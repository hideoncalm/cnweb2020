package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.mapper.UserMapper;
import com.cnweb2020.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quyenhaha
 */
public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public UserModel findUserByAccount(String userAccount, String password) {
        String sql = "select * from user where account = ?";
        // tra ve list co 1 phan tu hoac rong
        List<UserModel> results = query(sql, new UserMapper(), userAccount);
        if (results.isEmpty()) return null;  // user invalid
        else {
            UserModel userModel = results.get(0);
            if (userModel.getPassword().equals(password)) return userModel;
            else return null;   // password invalid
        }
    }

    @Override
    public boolean findUserByAccount(String userAccount) {
        String sql = "select * from user where account = ?";
        List<UserModel> results = query(sql, new UserMapper(), userAccount);
        return !results.isEmpty();
    }

    @Override
    // tra ve id cua user vua duoc them vao
    public int createUser(UserModel userModel) {
        String sql = "insert into user(account, password, fullName, phone, email, address , role) values(?, ?, ?, ?, ?, ? , ?)";
        return insert(sql, userModel.getAccount(), userModel.getPassword(), userModel.getFullName(),
                userModel.getPhone(), userModel.getEmail(), userModel.getAddress(), 0);
    }

    @Override
    public UserModel findUserById(int id) {
        String sql = "select * from user where id = ?";
        List<UserModel> users;
        users = query(sql, new UserMapper(), id);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
