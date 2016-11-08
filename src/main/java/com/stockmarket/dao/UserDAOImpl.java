package com.stockmarket.dao;

import com.stockmarket.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class UserDAOImpl implements UserDAO{
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /**
     * UserId is hidden in the template. It is used to recognize insert or update  action
     */
    @Override
    public void insertOrUpdate(User user) {
        if (user.getUserId() > 0) {
            // update user table
            String updateSql = "UPDATE users SET PASSWORD=? WHERE USER_ID=?";
            jdbcTemplate.update(updateSql, user.getUserPass(), user.getUserId());

        } else {
            // insert user table
            String userSql = "INSERT INTO users (USERNAME,PASSWORD)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(userSql, user.getUserName(),user.getUserPass());
            // insert userrole table
            String roleSql = "INSERT INTO user_roles (USERNAME,ROLE)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(roleSql, user.getUserName(),user.getUserRole());
        }
    }

    @Override
    public User getUser(String username){
        String sql = "SELECT * FROM users u WHERE u.username ='" + username + "'";

        return jdbcTemplate.queryForObject(sql,new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setUserId(rs.getInt("USER_ID"));
                        user.setUserName(rs.getString("username"));
                        user.setUserPass(rs.getString("password"));
                        return user;
                    }
                    });

    }
    //This function returns only number of rows.
    @Override
    public int findUsername(String username){
        String sql = "SELECT count(*) FROM users u WHERE u.username ='" + username + "'";
        return jdbcTemplate.queryForObject(sql,Integer.class );

    }
}
