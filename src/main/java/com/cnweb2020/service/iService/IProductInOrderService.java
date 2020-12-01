package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.Json2Model.UpdateProductInCartModel;

public interface IProductInOrderService {
    JsonReturnModel updateProductInCart(UpdateProductInCartModel update);
}
