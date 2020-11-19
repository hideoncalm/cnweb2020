package com.cnweb2020.service;
import com.cnweb2020.DAO.iDAO.IProductDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.ProductJsonModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.service.iService.IProductService;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ProductService implements IProductService{
    @Inject
    private IProductDAO productDAO ;

    @Override
    public ProductJsonModel findAll() {
        List<ProductModel> products;
        products = productDAO.findAll();
        if(products.isEmpty()) return new ProductJsonModel(CodeAndMessage.DATA_NOTFOUND,
                CodeAndMessage.DATA_NOTFOUND_PRODUCT, null);
        else return new ProductJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE,
                products);
    }

    @Override
    public ProductJsonModel findbyCategory(String category) {
        List<ProductModel> products;
        products = productDAO.findByCategory(category);
        if(products.isEmpty()) return new ProductJsonModel(CodeAndMessage.DATA_NOTFOUND,
                CodeAndMessage.DATA_NOTFOUND_CATEGORY, null);
        else return new ProductJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE,
                products);
    }

    @Override
    public ProductJsonModel findById(int id) {
        ProductModel productModel = productDAO.findById(id);
        List<ProductModel> productList = new ArrayList<>();
        if(productModel == null) return new ProductJsonModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCT, null);
        else{
            productList.add(productModel);
            return new ProductJsonModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, productList);
        }
    }


}
