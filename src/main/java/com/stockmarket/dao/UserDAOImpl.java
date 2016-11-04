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

    /**
     * UserId is hidden in the template
     * @param user
     */
    @Override
    public void saveOrUpdate(User user) {
        if (user.getUserId() > 0) {
            // update
            String sql = "UPDATE tickets SET TICKET_NO=?, TICKET_TITLE=?, TICKET_OWNER=?, "
                    + "CLUSTER=?, OPEN_DATE=?, CLOSE_DATE=?, DESCRIPTION=?" +
                    ",REPORTED_BY=?, PRIORITY=?, STATUS=?, ACC_OWNER=?, REQUEST_DATE=?,DUE_DATE =? WHERE ID=?";
            jdbcTemplate.update(sql, user.getUserName());
        } else {
            // insert user table
            String userSql = "INSERT INTO users (USERNAME,PASSWORD,WALLET_ID)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(userSql, user.getUserName(),user.getUserPass(), user.getUserWalletId());
            // insert userrole table
            String roleSql = "INSERT INTO user_roles (USERNAME,ROLE)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(roleSql, user.getUserName(),user.getUserRole());
        }
    }

    @Override
    public void delete(int ticketId) {
//        String sql = "DELETE FROM tickets WHERE ID=?";
//        jdbcTemplate.update(sql, ticketId);
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
// TODO create delete user