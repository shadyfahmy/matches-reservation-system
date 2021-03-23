package com.tickets_reservation_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.entity.Stadium;

import org.springframework.security.core.context.SecurityContextHolder;


import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stadium")
public class StadiumController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    
    /*
    This is an endpoint that creates a new stadium
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/createStadium")
    public ResponseEntity<Object> createStadium(@RequestBody Stadium stadium)
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();
        
        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return new ResponseEntity<>("ACCESS DENIED!", HttpStatus.FORBIDDEN);
        System.out.println("Trying to create stadium");
        String query = "INSERT INTO match_reservation_system.stadium VALUES (NULL, "
                        + "'" + stadium.getCity() + "', "
                        + "'" + stadium.getName() + "', "
                        + "'" + stadium.getNumber_of_rows() + "', "
                        + "'" + stadium.getSeats_per_row() + "');";

        try {
            jdbcTemplate.execute(query);
            System.out.println("Stadium created!");
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error adding stadium.", HttpStatus.FORBIDDEN);
        }
    }


}
