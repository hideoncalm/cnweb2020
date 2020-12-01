package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.JsonReturnModel;

public interface IProductService {
    JsonReturnModel findAll();
    JsonReturnModel findByCategory(String category);
    JsonReturnModel findById(int id);
    JsonReturnModel findByName(String productName);
}
