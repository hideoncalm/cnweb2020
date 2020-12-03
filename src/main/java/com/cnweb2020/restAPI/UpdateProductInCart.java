package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.Json2Model;
import com.cnweb2020.Json2Model.JsonReturnModel;
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


@WebServlet(urlPatterns = "/api-update-product-in-cart")
public class UpdateProductInCart extends HttpServlet {
    @Inject
    private IProductInOrderService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UpdateProductInCartModel update = Json2Model.of(request.getReader()).toModel(UpdateProductInCartModel.class);
            JsonReturnModel model = service.updateProductInCart(update);
            objectMapper.writeValue(response.getOutputStream(), model);
        } catch (Exception e) {
            objectMapper.writeValue(response.getOutputStream(),
                    new JsonReturnModel(CodeAndMessage.DATA_INVALID, CodeAndMessage.DATA_INVALID_JSON, null));
        }
    }

}
