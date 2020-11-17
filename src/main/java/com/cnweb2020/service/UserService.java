package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.UserJsonModel;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.UserModel;
import com.cnweb2020.service.iService.IUserService;
import javax.inject.Inject;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserService implements IUserService{

    @Inject
    private IUserDAO userDAO;

    @Inject
    private IOrdersDAO ordersDAO;
    @Override
    public UserJsonModel login(String userAccount, String password) {
        int userId =  userDAO.findUserByAccount(userAccount, password); // tra ve id or -1;
        if(userId == -1) return new UserJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_INVALID_USERNAME);
        else if(userId == -2) return new UserJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PASSWORD);
        else return new UserJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, Integer.toString(userId));
    }
    @Override
    public UserJsonModel creatUser(UserModel userModel) {
        String userAcccount = userModel.getAccount();
        if(userAcccount.length() < 5 || checkRegex(userAcccount)) return new UserJsonModel(CodeAndMessage.DATA_INVALID, CodeAndMessage.DATA_INVALID_USERNAME);
        boolean isFind = true;
        isFind = userDAO.findUserByAccount(userModel.getAccount());
        if(isFind) return new UserJsonModel(CodeAndMessage.DATA_DUPLICATED, CodeAndMessage.DATA_DUPLICATED_USERNAME);
        else{
            int userId =  userDAO.createUser(userModel);
            OrdersModel ordersModel = new OrdersModel(); // tao ra luon 1 gio hang cho user vua moi duoc tao
            ordersModel.setUserId(userId);
            ordersModel.setType(0);
            // luu orders cua user vua moi tao ra vao trong co so du lieu
            ordersDAO.insert(ordersModel);
            return new UserJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE);
        }
    }

    @Override
    public UserJsonModel findUserById(int id) {
        UserModel userModel =  userDAO.findUserById(id);
        if(userModel != null) return new UserJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE,
                Integer.toString(userModel.getId()), userModel.getFullName());
        else return new UserJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID);
    }

    private boolean checkRegex(String userAcccount) {
       Pattern p = Pattern.compile("[^a-zA-z0-9_]", Pattern.CASE_INSENSITIVE);
       Matcher m = p.matcher(userAcccount);
       return m.find(); // tra ve true neu co ky tu dac biet
    }
    
}
