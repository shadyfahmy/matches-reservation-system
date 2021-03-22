package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.entity.Stadium;
import com.tickets_reservation_system.entity.Team;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/match")
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
        System.out.println(((User)user).getUser_type());
        System.out.println("Test");
    }
    
    /*
    This is an endpoint that returns all available stadiums
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getStadiums")
    public List<Stadium> getStadiums()
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();

        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return Collections.emptyList();
        String query = "select * from stadium";
        System.out.println("All Stadiums Returned");

        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Stadium>() {
                        public Stadium mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Stadium s = new Stadium();
                            s.setId(rs.getInt("stadium_id"));
                            s.setCity(rs.getString("city"));
                            s.setName(rs.getString("name"));
                            s.setNumber_of_rows(rs.getInt("number_of_rows"));
                            s.setSeats_per_row(rs.getInt("seats_per_row"));
                            return s;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error retrieving stadiums at /getStadiums");
            System.out.println(e);
            return Collections.emptyList();
        }
    }


    /*
    This is an endpoint that returns all available teams
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getTeams")
    public List<Team> getTeams()
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();

        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return Collections.emptyList();
        String query = "select * from team";
        System.out.println("All Teams Returned");

        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Team>() {
                        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Team t = new Team();
                            t.setTeam_name(rs.getString("team_name"));
                            return t;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error retrieving teams at /getTeams");
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    
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
        String query = "INSERT INTO match_reservation_system.stadium VALUES (NULL, "
                        + "'" + stadium.getCity() + "', "
                        + "'" + stadium.getName() + "', "
                        + "'" + stadium.getNumber_of_rows() + "', "
                        + "'" + stadium.getSeats_per_row() + "');";

        try {
            jdbcTemplate.execute(query);
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error adding stadium.", HttpStatus.FORBIDDEN);
        }
    }

}
