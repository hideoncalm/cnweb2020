package com.cnweb2020.service;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.DAO.iDAO.IProductDAO;
import com.cnweb2020.DAO.iDAO.IProductInOrderDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.ProductInOrderJsonModel;
import com.cnweb2020.Json2Model.UpdateProductInCartModel;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.service.iService.IProductInOrderService;
import java.util.List;
import javax.inject.Inject;

public class ProductInOrderService implements IProductInOrderService {

    @Inject
    private IOrdersDAO ordersDAO;

    @Inject
    private IProductInOrderDAO productInOrderDAO;

    @Inject
    private IProductDAO productDAO;

    @Override
    public ProductInOrderJsonModel updateProductInCart(UpdateProductInCartModel update) {
        int userId = update.getUserId();
        int productId = update.getProductId();
        String type = update.getType();
        List<OrdersModel> orders = ordersDAO.findOrderByUserIdAndType(userId, 0);
        ProductModel productModel = productDAO.findById(productId);
        if (orders.isEmpty()) {
            return new ProductInOrderJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_USERID);
        } else if (productModel == null) {
            return new ProductInOrderJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCT);
        } else {
            int orderId = orders.get(0).getId();
            boolean isDone = productInOrderDAO.updateProductInCart(orderId, productId, type);
            if(isDone) return new ProductInOrderJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE);
            else return new ProductInOrderJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCTINCART);
        }

    }
}
