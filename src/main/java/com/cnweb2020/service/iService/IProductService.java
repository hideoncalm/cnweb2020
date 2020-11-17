package com.cnweb2020.service.iService;

import com.cnweb2020.Json2Model.ProductJsonModel;

public interface IProductService {
    ProductJsonModel findAll();
    ProductJsonModel findbyCategory(String category);
}
