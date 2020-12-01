package com.cnweb2020.service.iService;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.UserModel;

public interface IUserService {
    JsonReturnModel login(String userAccount, String password); // tra ve id or -1
    JsonReturnModel creatUser(UserModel userModel);  // tra ve -1 neu khong them thanh cong
    JsonReturnModel findUserById(int id);  // get user info
}
