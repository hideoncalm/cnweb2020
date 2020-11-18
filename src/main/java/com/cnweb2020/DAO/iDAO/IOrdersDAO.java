package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.ProductModel;
import java.util.List;

public interface IOrdersDAO extends IGenericDAO<OrdersModel>{
    List<ProductModel> getAllProductInCartByUserId(int userId);
    List<OrdersModel> findOrderByUserIdAndType(int userId, int type);
    void insert(OrdersModel ordersModel);
    void updateOrderPayment(OrdersModel ordersModel);
}
