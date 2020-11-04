/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnweb2020.Json2Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyenhaha
 */
public class Json2Model {

    private String value;

    public Json2Model(String value) {
        this.value = value;
    }
    // chuyển từ 1 string value ra model
    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (IOException ex) {
            Logger.getLogger(Json2Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    // nhan vao json và trả về 1 string value;
    public static Json2Model of(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Json2Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Json2Model(sb.toString());
    }

}
