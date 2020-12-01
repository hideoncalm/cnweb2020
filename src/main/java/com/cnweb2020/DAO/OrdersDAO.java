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
        List<OrdersModel> orders = findOrderByUserIdAndType(userId, 0);
        if (orders.isEmpty()) {
            return null;
        } else {
            int orderId = orders.get(0).getId();
            String sql = "select * from productInOrder where orderId = ?";
            // products gom 3 thanh phan productId, orderId, quatity
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
    public List<OrdersModel> findOrderByUserIdAndType(int userId, int type) {
        String sql = "select * from orders where userId = ? and type = ?";
        List<OrdersModel> orders = null;
        orders = query(sql, new OrdersMapper(), userId, type);
        return orders;
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

    @Override
    public void updateOrderPayment(OrdersModel ordersModel) {
        String sql = "update orders set personTakeOrder = ?, phone = ?, address = ?, type = ?, time = ? where id = ?";
        update(sql, ordersModel.getPersonTakeOrder(), ordersModel.getPhone(), ordersModel.getAddress(),
                ordersModel.getType(), ordersModel.getTime(), ordersModel.getId());
    }

}
