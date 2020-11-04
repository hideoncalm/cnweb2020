package com.cnweb2020.mapper;

import com.cnweb2020.model.ProductModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductMapper implements RowMapper<ProductModel> {

    @Override
    public ProductModel mapRow(ResultSet resultSet) {
        try {
            ProductModel productModel = new ProductModel();
            productModel.setId(resultSet.getInt("id"));
            productModel.setName(resultSet.getString("name"));
            productModel.setCategory(resultSet.getString("category"));
            productModel.setDescription(resultSet.getString("description"));
            productModel.setImage(resultSet.getString("image"));
            productModel.setPrice(resultSet.getInt("price"));
            return productModel;
        } catch (SQLException ex) {
            Logger.getLogger(ProductMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
