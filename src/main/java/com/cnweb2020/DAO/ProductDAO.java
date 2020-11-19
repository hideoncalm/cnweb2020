
package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IProductDAO;
import com.cnweb2020.mapper.ProductMapper;
import com.cnweb2020.model.ProductModel;
import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO{

    @Override
    public List<ProductModel> findAll() {
        String sql = "select * from product";
        return query(sql, new ProductMapper());
    }

    @Override
    public List<ProductModel> findByCategory(String category) {
        String sql = "select * from product where category = ?";
        return query(sql, new ProductMapper(), category);
    }

    @Override
    public ProductModel findById(int productId) {
        List<ProductModel> products;
        String sql = "select * from product where id = ?";
        products = query(sql, new ProductMapper(), productId);
        if(products.isEmpty()) return null;
        return products.get(0);
    }
  
}
