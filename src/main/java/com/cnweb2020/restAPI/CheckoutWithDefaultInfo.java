package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.Json2Model;
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

@WebServlet(urlPatterns = "/api-checkout-with-default-info")
public class CheckoutWithDefaultInfo extends HttpServlet {

    @Inject
    private IOrdersService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        OrdersModel ordersModel = Json2Model.of(request.getReader()).toModel(OrdersModel.class);
        CodeAndMessage json = service.checkoutWithDefaultInfo(ordersModel);
        objectMapper.writeValue(response.getOutputStream(), json);
    }

}
