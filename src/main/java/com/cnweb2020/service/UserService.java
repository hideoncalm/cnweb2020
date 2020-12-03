package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.UserModel;
import com.cnweb2020.service.iService.IUserService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Inject
    private IOrdersDAO ordersDAO;

    @Override
    public JsonReturnModel login(String userAccount, String password) {
        if(userAccount.contains("<script>") || password.contains("<script>"))
            return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_INVALID_PASSWORD, null);
        UserModel userModel = userDAO.findUserByAccount(userAccount, password); // tra ve id or -1;
        if(userModel == null)
            return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_INVALID_PASSWORD, null);
        else {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId", userModel.getId());
            map.put("role", userModel.getRole());
            return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, map);
        }
    }

    @Override
    public JsonReturnModel creatUser(UserModel userModel) {
        if (checkRegex(userModel))
            return new JsonReturnModel(CodeAndMessage.DATA_INVALID, CodeAndMessage.DATA_INVALID_USERNAME, null);
        boolean isFind = userDAO.findUserByAccount(userModel.getAccount());
        if (isFind)
            return new JsonReturnModel(CodeAndMessage.DATA_DUPLICATED, CodeAndMessage.DATA_DUPLICATED_USERNAME, null);
        else {
            int userId = userDAO.createUser(userModel);
            OrdersModel ordersModel = new OrdersModel(); // tao ra luon 1 gio hang cho user vua moi duoc tao
            ordersModel.setUserId(userId);
            ordersModel.setType(0);
            // luu orders cua user vua moi tao ra vao trong co so du lieu
            ordersDAO.insert(ordersModel);
            return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, null);
        }
    }

    @Override
    public JsonReturnModel findUserById(int id) {
        UserModel userModel = userDAO.findUserById(id);
        if (userModel != null)
            return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, userModel);
        else return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID, null);
    }

    private boolean checkRegex(UserModel userModel) {
        String script = "<script>";
        String account = userModel.getAccount();
        String password = userModel.getPassword();
        String phone = userModel.getPhone();
        String email = userModel.getEmail();
        String address = userModel.getAddress();
        String fullName = userModel.getFullName();

        Pattern p = Pattern.compile("[^a-zA-z0-9_]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(account);
        // check ki tu va <script></script>
        return m.find() || account.contains(script) || password.contains(script) || phone.contains(script) ||
                account.length() < 5 || email.contains(script) || address.contains(script) || fullName.contains(script);
    }

}
