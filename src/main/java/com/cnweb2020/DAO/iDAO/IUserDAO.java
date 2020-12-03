
package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel>{
    boolean findUserByAccount(String userAccount);
    UserModel findUserByAccount(String userAccount, String password);
    int createUser(UserModel userModel);
    UserModel findUserById(int id);
}
