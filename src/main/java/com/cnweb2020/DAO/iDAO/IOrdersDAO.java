package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.ProductModel;
import java.util.List;

public interface IOrdersDAO extends IGenericDAO<OrdersModel>{
    List<ProductModel> getAllProductInCartByUserId(int userId);
    OrdersModel findOrderByUserId(int userId);
    void insert(OrdersModel ordersModel);
}
