
package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel>{
    boolean findUserByAccount(String userAccount);
    int findUserByAccount(String userAccount, String password); // tra ve id or -1
    int createUser(UserModel userModel);
    UserModel findUserById(int id);
}
