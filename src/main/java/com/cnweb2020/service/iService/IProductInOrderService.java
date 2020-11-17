package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.ProductInOrderJsonModel;
import com.cnweb2020.Json2Model.UpdateProductInCartModel;

public interface IProductInOrderService {

    ProductInOrderJsonModel updateProductInCart(UpdateProductInCartModel update);
    
}
