package com.cnweb2020.service;
import com.cnweb2020.DAO.iDAO.IProductDAO;
import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.service.iService.IProductService;

import java.util.List;
import javax.inject.Inject;

public class ProductService implements IProductService{
    @Inject
    private IProductDAO productDAO ;

    @Override
    public JsonReturnModel findAll() {
        List<ProductModel> products;
        products = productDAO.findAll();
        if(products.isEmpty()) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND,
                CodeAndMessage.DATA_NOTFOUND_PRODUCT, products);
        else return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE,
                products);
    }

    @Override
    public JsonReturnModel findByCategory(String category) {
        List<ProductModel> products;
        products = productDAO.findByCategory(category);
        if(products.isEmpty()) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND,
                CodeAndMessage.DATA_NOTFOUND_CATEGORY, null);
        else return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE,
                products);
    }

    @Override
    public JsonReturnModel findById(int id) {
        List<ProductModel> products = productDAO.findById(id);
        if(products.isEmpty()) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCT, products);
        else return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, products);
    }

    @Override
    public JsonReturnModel findByName(String productName) {
        List<ProductModel> products = productDAO.findByName(productName);
        if(products.isEmpty()) return new JsonReturnModel(CodeAndMessage.DATA_NOTFOUND, CodeAndMessage.DATA_NOTFOUND_PRODUCT, products);
        else return new JsonReturnModel(CodeAndMessage.SUCCESS, CodeAndMessage.SUCCESS_MESSAGE, products);
    }


}
