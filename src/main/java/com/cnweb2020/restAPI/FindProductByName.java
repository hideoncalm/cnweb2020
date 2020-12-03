package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.CodeAndMessage;
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

@WebServlet(urlPatterns = "/api-find-product-by-name")
public class FindProductByName extends HttpServlet {
    @Inject
    private IProductService productService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductModel productModel = Json2Model.of(request.getReader()).toModel(ProductModel.class);
            String productName = productModel.getName();
            JsonReturnModel jsonModel = productService.findByName(productName);
            objectMapper.writeValue(response.getOutputStream(), jsonModel);
        } catch (Exception e) {
            objectMapper.writeValue(response.getOutputStream(),
                    new JsonReturnModel(CodeAndMessage.DATA_INVALID, CodeAndMessage.DATA_INVALID_JSON, null));
        }
    }
}
