package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MatchController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    This is an endpoint that doesn't check the header for a token.(Add any similar path to security config)
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public void test() {
        System.out.println("Test");
    }

    /*
    This is an endpoint that checks the header for a token.
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test2")
    public void test2()
    {
        // Example get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        System.out.println(((User)user).getUsername());
        System.out.println(((User)user).getMail());
        System.out.println("Test");
    }
}
