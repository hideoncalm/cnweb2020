package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.mapper.UserMapper;
import com.cnweb2020.model.UserModel;
import java.util.List;

/**
 *
 * @author quyenhaha
 */
public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

    @Override
    public List<UserModel> findAll() {
        String sql = "select * form user";
        return query(sql, new UserMapper());
    }  

    @Override
    public boolean findUserByAccount(String userAccount, String password) {
        String sql = "select account form user where account = ?";
        // tra ve list co 1 phan tu hoac rong
        List<UserModel> results = query(sql, new UserMapper(), userAccount);
        if(results.size() == 0) return false;
        else if(results.get(0).getPassword().equals(password)) return true;
        else return false;
    }
}
