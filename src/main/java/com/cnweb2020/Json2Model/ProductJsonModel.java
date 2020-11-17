package com.cnweb2020.Json2Model;

import com.cnweb2020.model.ProductModel;
import java.util.List;

public class ProductJsonModel extends CodeAndMessage{
 
    private List<ProductModel> listProduct;

    public ProductJsonModel(int code, String message, List<ProductModel> listProduct) {
        super(code, message);
        this.listProduct = listProduct;
    }

    public List<ProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductModel> listProduct) {
        this.listProduct = listProduct;
    }
    
}
