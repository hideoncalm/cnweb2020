package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.DAO.iDAO.IProductInOrderDAO;
import com.cnweb2020.DAO.iDAO.IUserDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.ProductJsonModel;
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
    public ProductJsonModel getAllProductInCartByUserId(int userId) {
        List<ProductModel> listProducts = ordersDAO.getAllProductInCartByUserId(userId);
        if(listProducts == null) return new ProductJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID, null);
        else return new ProductJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, listProducts);
    }

    @Override
    public CodeAndMessage checkoutWithNewInfo(OrdersModel ordersModel) {
        int userId = ordersModel.getUserId();
        List<OrdersModel> orders = ordersDAO.findOrderByUserIdAndType(userId, 0);
        if(orders.isEmpty()) return new CodeAndMessage(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID);
        else{
            OrdersModel order = orders.get(0);
            // cart rong
            if(!productInOrderDAO.findByOrderId(order.getId())) return new CodeAndMessage(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCTINCART);
            order.setAddress(ordersModel.getAddress());
            order.setPersonTakeOrder(ordersModel.getPersonTakeOrder());
            order.setPhone(ordersModel.getPhone());
            order.setType(1);
            order.setTime(new Timestamp(new Date().getTime()));
            ordersDAO.updateOrderPayment(order);
            OrdersModel newOrder = new OrdersModel();
            newOrder.setUserId(userId);
            newOrder.setType(0);
            ordersDAO.insert(newOrder);
            return new CodeAndMessage(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE);
        }
    }

    @Override
    public CodeAndMessage checkoutWithDefaultInfo(OrdersModel ordersModel) {
        int userId = ordersModel.getUserId();
        UserModel user = userDAO.findUserById(userId);
        if(user == null) return new CodeAndMessage(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID);
        OrdersModel order = ordersDAO.findOrderByUserIdAndType(userId, 0).get(0);
        if(!productInOrderDAO.findByOrderId(order.getId())) return new CodeAndMessage(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCTINCART);
        order.setAddress(user.getAddress());
        order.setPersonTakeOrder(user.getFullName());
        order.setPhone(user.getPhone());
        order.setType(1);
        order.setTime(new Timestamp(new Date().getTime()));
        ordersDAO.updateOrderPayment(order);
        OrdersModel newOrder = new OrdersModel();
        newOrder.setUserId(userId);
        newOrder.setType(0);
        ordersDAO.insert(newOrder);
        return new CodeAndMessage(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE);
    }
    
}
