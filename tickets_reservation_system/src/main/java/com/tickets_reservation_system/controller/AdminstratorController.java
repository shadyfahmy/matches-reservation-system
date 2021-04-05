package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Optional.empty;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/adminstrator")
public class AdminstratorController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    AdminstratorController() {
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/listPending")
    public List<User> listPending() {
        String query = "select * from user where approved = 0";
        try {
            return jdbcTemplate.query(query,
                    new RowMapper<User>() {
                        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                            User u = new User();
                            u.setUsername(rs.getString("username"));
                            u.setPw(rs.getString("pw"));
                            u.setApproved(rs.getInt("approved"));
                            u.setAddress(rs.getString("address"));
                            u.setCity(rs.getString("city"));
                            u.setDob(rs.getDate("dob").toString());
                            u.setFname(rs.getString("fname"));
                            u.setLname(rs.getString("lname"));
                            u.setMail(rs.getString("mail"));
                            u.setGender(rs.getString("gender"));
                            u.setUser_type(rs.getString("user_type"));
                            return u;
                        }
                });
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/listAllAccounts")
    public List<User> listAllAccounts() {
        String query = "select * from user";
        try {
            return jdbcTemplate.query(query,
                    new RowMapper<User>() {
                        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                            User u = new User();
                            u.setUsername(rs.getString("username"));
                            u.setPw(rs.getString("pw"));
                            u.setApproved(rs.getInt("approved"));
                            u.setAddress(rs.getString("address"));
                            u.setCity(rs.getString("city"));
                            u.setDob(rs.getDate("dob").toString());
                            u.setFname(rs.getString("fname"));
                            u.setLname(rs.getString("lname"));
                            u.setMail(rs.getString("mail"));
                            u.setGender(rs.getString("gender"));
                            u.setUser_type(rs.getString("user_type"));
                            return u;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/accept")
    public void accept(@RequestBody User user) {
        String query = "UPDATE user SET approved = 1 where username = '" + user.getUsername()+"'";

        System.out.println(query);
        try{
            jdbcTemplate.execute(query);
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void delete(@RequestParam String username) {
        String query = "DELETE FROM user where username = '" + username + "'";
        try{
            jdbcTemplate.execute(query);
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
        }
    }
}
