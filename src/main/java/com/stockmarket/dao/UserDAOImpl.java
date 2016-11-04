package com.stockmarket.dao;

import com.stockmarket.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class UserDAOImpl implements UserDAO{
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> listuser(String userName) {
        String sql = "SELECT * FROM users u, user_roles ur WHERE u.username = ur.username AND u.username ='" + userName + "'";
        List<User> listuser = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("ID"));
                user.setUserName(rs.getString("username"));
                user.setUserPass(rs.getString("password"));
                user.setUserRole(rs.getString("role"));

                return user;
            }

        });

        return listuser;
    }
}
