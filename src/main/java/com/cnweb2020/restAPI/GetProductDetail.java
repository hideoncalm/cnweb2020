package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.Json2Model;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.ProductModel;
import com.cnweb2020.service.iService.IProductService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-get-product-detail")
public class GetProductDetail extends HttpServlet {
    @Inject
    private IProductService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        ProductModel productModel = Json2Model.of(request.getReader()).toModel(ProductModel.class);
        int id = productModel.getId();
        JsonReturnModel code = service.findById(id);
        objectMapper.writeValue(response.getOutputStream(), code);
    }
}
