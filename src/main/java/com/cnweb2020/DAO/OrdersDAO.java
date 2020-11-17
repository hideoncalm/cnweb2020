package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IOrdersDAO;
import com.cnweb2020.mapper.OrdersMapper;
import com.cnweb2020.mapper.ProductInOrderMapper;
import com.cnweb2020.mapper.ProductMapper;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.model.ProductInOrder;
import com.cnweb2020.model.ProductModel;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO extends AbstractDAO<OrdersModel> implements IOrdersDAO {

    @Override
    public List<ProductModel> getAllProductInCartByUserId(int userId) {
        List<ProductModel> productModels = new ArrayList<>();
        OrdersModel ordersModel = findOrderByUserId(userId);
        if (ordersModel == null) {
            return null;
        } else {
            int orderId = ordersModel.getId();
            String sql = "select * from productInOrder where orderId = ?";

            // products gom 3 thanh phan userId, orderId, quatity
            List<ProductInOrder> products = query(sql, new ProductInOrderMapper(), orderId);
            for (ProductInOrder productInOrder : products) {
                ProductModel productModel = findProductById(productInOrder.getProductId());
                productModel.setQuantity(productInOrder.getQuantity());
                productModels.add(productModel);
            }
            return productModels;
        }
    }

    @Override
    public OrdersModel findOrderByUserId(int userId) {
        String sql = "select * from orders where userId = ?";
        List<OrdersModel> orders;
        orders = query(sql, new OrdersMapper(), userId);
        if (orders.isEmpty()) {
            return null;
        } else {
            return orders.get(0);
        }
    }

    public ProductModel findProductById(int productId) {
        String sql = "select * from product where id = ?";
        return query(sql, new ProductMapper(), productId).get(0);
    }

    @Override
    public void insert(OrdersModel ordersModel) {
        String sql = "insert into orders(userId, type) values(?, ?)";
        update(sql, ordersModel.getUserId(), ordersModel.getType());
    }

}
