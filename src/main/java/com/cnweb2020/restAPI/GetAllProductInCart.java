package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.Json2Model;
import com.cnweb2020.Json2Model.ProductJsonModel;
import com.cnweb2020.model.OrdersModel;
import com.cnweb2020.service.iService.IOrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api-get-all-product-in-cart")
public class GetAllProductInCart extends HttpServlet {
    @Inject
    private IOrdersService ordersService;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        
        OrdersModel ordersModel = Json2Model.of(request.getReader()).toModel(OrdersModel.class);
        int userId = ordersModel.getUserId();
        ProductJsonModel productJsonModel = ordersService.getAllProductInCartByUserId(userId);
        objectMapper.writeValue(response.getOutputStream(), productJsonModel);
    }
}
