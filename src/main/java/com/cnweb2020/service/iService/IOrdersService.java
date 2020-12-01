package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.OrdersModel;

public interface IOrdersService {

    JsonReturnModel getAllProductInCartByUserId(int userId);
    JsonReturnModel checkoutWithNewInfo(OrdersModel ordersModel);
    JsonReturnModel checkoutWithDefaultInfo(OrdersModel ordersModel);
}
