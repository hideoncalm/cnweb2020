
package com.cnweb2020.Json2Model;

public class UpdateProductInCartModel {
    private int userId;
    private int productId;
    private String type;  // remove, add,

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
