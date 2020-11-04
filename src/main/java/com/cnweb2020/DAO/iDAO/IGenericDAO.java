package com.cnweb2020.DAO.iDAO;

import com.cnweb2020.mapper.RowMapper;
import java.util.List;

public interface IGenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
}
