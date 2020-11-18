package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.Json2Model;
import com.cnweb2020.Json2Model.ProductInOrderJsonModel;
import com.cnweb2020.Json2Model.UpdateProductInCartModel;
import com.cnweb2020.service.iService.IProductInOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/api-checkout_with_new_info")
public class UpdateProductInCart extends HttpServlet{
    @Inject
    private IProductInOrderService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();        
        UpdateProductInCartModel update = Json2Model.of(request.getReader()).toModel(UpdateProductInCartModel.class);  
        ProductInOrderJsonModel model = service.updateProductInCart(update);
        objectMapper.writeValue(response.getOutputStream(), model);
        
    }

}
