/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.model.ProductInOrder;

/**
 *
 * @author quyenhaha
 */
public interface IProductInOrderDAO extends IGenericDAO<ProductInOrder>{
    boolean updateProductInCart(int orderId, int productId, String type);
    boolean findByOrderIdAndProductId(int orderId, int productId);
    boolean findByOrderId(int orderId);
}
