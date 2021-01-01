package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public void signup(@RequestBody User user) {
        String query = "INSERT INTO match_reservation_system.user VALUES ("
                + "'" + user.getUsername() + "', "
                + "'" + user.getPw() + "', "
                + "'" + user.getFname() + "', "
                + "'" + user.getLname() + "', "
                + "'" + user.getDob() + "', "
                + "'" + user.getGender() + "', "
                + "'" + user.getCity() + "', "
                + "'" + user.getAddress() + "', "
                + "'" + user.getMail() + "', "
                + "'" + user.getUser_type() + "', "
                + "0"                           // User not approved yet
                + ");";
        System.out.println(query);
        try {
            jdbcTemplate.execute(query);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
        }
    }
}
