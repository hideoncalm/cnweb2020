package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.DAO.iDAO.IProductInOrderDAO;
import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.model.UserModel;
import com.cnweb2020.service.iService.IOrdersService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class OrdersService implements IOrdersService{
    
    @Inject
    private IOrdersDAO ordersDAO;
    @Inject
    private IUserDAO userDAO;
    @Inject
    private IProductInOrderDAO productInOrderDAO;
    @Override
    public JsonReturnModel getAllProductInCartByUserId(int userId) {
        List<ProductModel> listProducts = ordersDAO.getAllProductInCartByUserId(userId);
        if(listProducts == null) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID, null);
        else return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, listProducts);
    }

    @Override
    public JsonReturnModel checkoutWithNewInfo(OrdersModel ordersModel) {
        String address = ordersModel.getAddress();
        String personTakeOrder = ordersModel.getPersonTakeOrder();
        String phone = ordersModel.getPhone();
        if(address.contains("<script>") || personTakeOrder.contains("<script>") || phone.contains("<script>"))
            return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_INVALID_EMAIL,null);
        int userId = ordersModel.getUserId();
        List<OrdersModel> orders = ordersDAO.findOrderByUserIdAndType(userId, 0);
        if(orders.isEmpty()) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID,null);
        else{
            OrdersModel order = orders.get(0);
            // cart rong
            if(!productInOrderDAO.findByOrderId(order.getId()))
                return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCTINCART, null);
            order.setAddress(address);
            order.setPersonTakeOrder(personTakeOrder);
            order.setPhone(phone);
            order.setType(1);
            order.setTime(new Timestamp(new Date().getTime()));
            // update thong tin order chuyen type = 1 va tao ra 1 order moi co type = 0
            ordersDAO.updateOrderPayment(order);
            OrdersModel newOrder = new OrdersModel();
            newOrder.setUserId(userId);
            newOrder.setType(0);
            ordersDAO.insert(newOrder);
            return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, null);
        }
    }

    @Override
    public JsonReturnModel checkoutWithDefaultInfo(OrdersModel ordersModel) {
        int userId = ordersModel.getUserId();
        UserModel user = userDAO.findUserById(userId);
        if(user == null) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID, null);
        OrdersModel order = ordersDAO.findOrderByUserIdAndType(userId, 0).get(0);
        // cart rong
        if(!productInOrderDAO.findByOrderId(order.getId())) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCTINCART, null);
        order.setAddress(user.getAddress());
        order.setPersonTakeOrder(user.getFullName());
        order.setPhone(user.getPhone());
        order.setType(1);
        order.setTime(new Timestamp(new Date().getTime()));
        // update thong tin order chuyen type = 1 va tao ra 1 order moi co type = 0
        ordersDAO.updateOrderPayment(order);
        OrdersModel newOrder = new OrdersModel();
        newOrder.setUserId(userId);
        newOrder.setType(0);
        ordersDAO.insert(newOrder);
        return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, null);
    }
    
}
