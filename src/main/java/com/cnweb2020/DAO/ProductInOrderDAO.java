package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IProductInOrderDAO;
import com.cnweb2020.mapper.ProductInOrderMapper;
import com.cnweb2020.model.ProductInOrder;
import java.util.ArrayList;
import java.util.List;

public class ProductInOrderDAO extends AbstractDAO<ProductInOrder> implements IProductInOrderDAO {

    @Override
    public boolean updateProductInCart(int orderId, int productId, String type) {
        String sql_select = "select * from productInOrder where orderId = ? and productId = ?";
        String sql_insert = "insert into productInOrder(orderId, productId, quantity) values(?, ?, ?)";
        String sql_update = "update productInOrder set quantity = ? where orderId = ? and productId = ?";
        String sql_delete = "delete from productInOrder where orderId = ? and productId = ?";
        ProductInOrder p = new ProductInOrder();
        List<ProductInOrder> productInOrder = new ArrayList<>();
        productInOrder = query(sql_select, new ProductInOrderMapper(), orderId, productId);
        if (type.equals("add")) {
            if (productInOrder.isEmpty()) {
                p.setQuantity(1);
                p.setOrderId(orderId);
                p.setProductId(productId);
                update(sql_insert, p.getOrderId(), p.getProductId(), p.getQuantity());
            } else {
                p = productInOrder.get(0);
                int quantity = p.getQuantity();
                p.setQuantity(quantity + 1);
                update(sql_update, p.getQuantity(), p.getOrderId(), p.getProductId());
            }
        } else {
            if (productInOrder.isEmpty()) return false;
            p = productInOrder.get(0);
            int quantity = p.getQuantity();
            if (quantity == 1) {
                update(sql_delete, p.getOrderId(), p.getProductId());
            } else {
                p.setQuantity(quantity - 1);
                update(sql_update, p.getQuantity(), p.getOrderId(), p.getProductId());
            }
        }
        return true;
    }

    @Override
    public boolean findByOrderIdAndProductId(int orderId, int productId) {
        String sql = "select * from productInOrder where orderId = ? and productId = ?";
        List<ProductInOrder> products = new ArrayList<>();
        products = query(sql, new ProductInOrderMapper(), orderId, productId);
        if(products.isEmpty()) return false;
        else return true;
    }

    @Override
    public boolean findByOrderId(int orderId) {
        String sql = "select * from productInOrder where orderId = ?";
        List<ProductInOrder> products;
        products = query(sql, new ProductInOrderMapper(), orderId);
        if(products.isEmpty()) return false;
        else return true;
    }

  

}
