package com.cnweb2020.service.iService;
import com.cnweb2020.Json2Model.ProductJsonModel;

public interface IOrdersService {
  ProductJsonModel getAllProductInCartByUserId(int userId);
}
