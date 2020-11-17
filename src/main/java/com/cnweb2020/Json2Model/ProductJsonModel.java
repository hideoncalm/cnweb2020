package com.cnweb2020.Json2Model;

import com.cnweb2020.model.ProductModel;
import java.util.List;

public class ProductJsonModel{
    private int code;
    private String massage;
    private List<ProductModel> listProduct;

    public ProductJsonModel(int code, String massage, List<ProductModel> listProduct) {
        this.code = code;
        this.massage = massage;
        this.listProduct = listProduct;
    }
    
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public List<ProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductModel> listProduct) {
        this.listProduct = listProduct;
    }
    
}
