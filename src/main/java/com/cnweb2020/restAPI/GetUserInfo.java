
package com.cnweb2020.restAPI;

import com.cnweb2020.Json2Model.CodeAndMessage;
import com.cnweb2020.Json2Model.Json2Model;
import com.cnweb2020.Json2Model.JsonReturnModel;
import com.cnweb2020.model.UserModel;
import com.cnweb2020.service.iService.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api-get-user-info")
public class GetUserInfo extends HttpServlet {
    @Inject
    private IUserService userService;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserModel userModel = Json2Model.of(request.getReader()).toModel(UserModel.class);
            int id = userModel.getId();
            JsonReturnModel userJsonModel = userService.findUserById(id);
            objectMapper.writeValue(response.getOutputStream(), userJsonModel);
        } catch (Exception e) {
            objectMapper.writeValue(response.getOutputStream(),
                    new JsonReturnModel(CodeAndMessage.DATA_INVALID, CodeAndMessage.DATA_INVALID_JSON, null));
        }
    }
}
