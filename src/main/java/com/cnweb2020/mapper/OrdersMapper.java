
package com.cnweb2020.mapper;

import com.cnweb2020.model.OrdersModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersMapper implements RowMapper<OrdersModel>{

    @Override
    public OrdersModel mapRow(ResultSet resultSet) {
        try {
            OrdersModel ordersModel = new OrdersModel();
            ordersModel.setId(resultSet.getInt("id"));
            ordersModel.setTime(resultSet.getTimestamp("time"));
            ordersModel.setType(resultSet.getInt("type"));
            ordersModel.setUserId(resultSet.getInt("userId"));
            ordersModel.setAddress(resultSet.getString("address"));
            ordersModel.setPersonTakeOrder(resultSet.getString("personTakeOrder"));
            ordersModel.setPhone(resultSet.getString("phone"));
            return ordersModel;
        } catch (SQLException ex) {
            Logger.getLogger(OrdersMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
