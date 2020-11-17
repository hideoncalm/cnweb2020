package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.ProductJsonModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.service.iService.IOrdersService;
import java.util.List;
import javax.inject.Inject;

public class OrdersService implements IOrdersService{
    
    @Inject
    private IOrdersDAO ordersDAO;
    @Override
    public ProductJsonModel getAllProductInCartByUserId(int userId) {
        List<ProductModel> listProducts = ordersDAO.getAllProductInCartByUserId(userId);
        if(listProducts == null) return new ProductJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID, null);
        else return new ProductJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, listProducts);
    }
    
}
