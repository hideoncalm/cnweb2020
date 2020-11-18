package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.ProductJsonModel;
import com.cnweb2020.model.OrdersModel;

public interface IOrdersService {

    ProductJsonModel getAllProductInCartByUserId(int userId);
    CodeAndMessage checkoutWithNewInfo(OrdersModel ordersModel);
    CodeAndMessage checkoutWithDefaultInfo(OrdersModel ordersModel);
}
