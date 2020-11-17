package com.cnweb2020.DAO;

import com.cnweb2020.DAO.iDAO.IGenericDAO;
import com.cnweb2020.mapper.RowMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> implements IGenericDAO<T> {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // load Driver mysql
            String url = "jdbc:mysql://127.0.0.1:3306/cnweb_20201";
            String user = "root";
            String password = "Anhquyen123";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //set params
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                connection.close();
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    }

    private void setParameters(PreparedStatement statement, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            try {
                int index = i + 1;
                Object param = parameters[i];
                if (param instanceof Integer) {
                    statement.setInt(index, (int) param);
                } else if (param instanceof String) {
                    statement.setString(index, (String) param);
                } else if (param instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) param);
                } else if (param == null) {
                    statement.setNull(index, Types.NULL);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = -1; // id tra cua user moi vua them vao
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            // set param
            setParameters(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.close();
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            // set param
            setParameters(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connection.close();
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
