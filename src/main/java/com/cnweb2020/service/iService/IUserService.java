package com.cnweb2020.service.iService;
import com.cnweb2020.Json2Model.UserJsonModel;
import com.cnweb2020.model.UserModel;

public interface IUserService {
    UserJsonModel login(String userAccount, String password); // tra ve id or -1
    UserJsonModel creatUser(UserModel userModel);  // tra ve -1 neu khong them thanh cong
    UserJsonModel findUserById(int id);  // get user info
}
