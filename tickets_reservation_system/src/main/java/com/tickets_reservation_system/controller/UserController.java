package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    This endpoint enable user to edit his data in the database.
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{changePw}")
    public ResponseEntity<String> edit(@RequestBody User modifiedUser,
                     @PathVariable("changePw") Boolean changePw)
    {
        // Example get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String query = "UPDATE match_reservation_system.user SET ";
        if(changePw) {
            query = query + "pw = '" + passwordEncoder.encode(modifiedUser.getPw()) + "', ";
        }
        query = query
                + "fname = '" + modifiedUser.getFname() + "', "
                + "lname = '" + modifiedUser.getLname() + "', "
                + "dob = '" + modifiedUser.getDob() + "', "
                + "gender = '" + modifiedUser.getGender() + "', "
                + "city = '" + modifiedUser.getCity() + "', "
                + "address = '" + modifiedUser.getAddress() + "', "
                + "mail = '" + modifiedUser.getMail() + "' "
                + "WHERE username = '" + ((User)user).getUsername() +"';";

        try {
            jdbcTemplate.execute(query);
            return new ResponseEntity<>("User Edited successfully", HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("User Not Edited, Email is already registered", HttpStatus.CONFLICT);
        }
    }


}
