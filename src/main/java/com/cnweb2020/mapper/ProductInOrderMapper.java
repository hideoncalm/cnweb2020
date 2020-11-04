package com.cnweb2020.mapper;

import com.cnweb2020.model.ProductInOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductInOrderMapper implements RowMapper<ProductInOrder>{

    @Override
    public ProductInOrder mapRow(ResultSet resultSet) {
        try {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setOrderId(resultSet.getInt("orderId"));
            productInOrder.setProductId(resultSet.getInt("productId"));
            productInOrder.setQuantity(resultSet.getInt("quantity"));
            return productInOrder;
        } catch (SQLException ex) {
            Logger.getLogger(ProductInOrderMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
