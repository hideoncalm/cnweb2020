/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.ProductModel;
import java.util.List;

public interface IProductDAO extends IGenericDAO<ProductModel>{
    List<ProductModel> findAll(); // tra ve danh sach san pham 
    List<ProductModel> findByCategory(String category);
    ProductModel findById(int productId);
    List<ProductModel> findByName(String productName);
}
