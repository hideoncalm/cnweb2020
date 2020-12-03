package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.service.iService.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api-get-all-product")
public class GetAllProductAPI extends HttpServlet {
    @Inject
    private IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonReturnModel productJsonModel = productService.findAll();
        objectMapper.writeValue(response.getOutputStream(), productJsonModel);
    }
}
