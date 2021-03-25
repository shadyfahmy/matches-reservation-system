package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.entity.Stadium;
import com.tickets_reservation_system.entity.Team;
import com.tickets_reservation_system.entity.Match;

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
        /*Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();

        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return Collections.emptyList();*/
        String query = "select * from stadium order by stadium_id asc";
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
       This is an endpoint that returns all available matches
       */
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
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getMatches")
    public List<Match> getMatches()
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();

        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return Collections.emptyList();
        String query = "select * from match_reservation_system.match";
        System.out.println("Returning All Matches");

        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Match>() {
                        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Match m = new Match();
                            m.setMatchId(rs.getInt("match_id"));
                            m.setHome_team(rs.getString("home_team"));
                            m.setAway_team(rs.getString("away_team"));
                            m.setMatch_Date_time(rs.getString("match_date_time"));
                            m.setMain_refree(rs.getString("main_refree"));
                            m.setLinesman1(rs.getString("linesman1"));
                            m.setLinesman2(rs.getString("linesman2"));
                            m.setStadium_id(rs.getInt("stadium_id"));
                            return m;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error retrieving matches at /getMatches");
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/allmatches")
    public List<Match> getAllMatches()
    {

        String query = "select * from match_reservation_system.match order by match_date_time desc;";
        System.out.println("Returning All Matches");

        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Match>() {
                        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Match m = new Match();
                            m.setMatchId(rs.getInt("match_id"));
                            m.setHome_team(rs.getString("home_team"));
                            m.setAway_team(rs.getString("away_team"));
                            m.setMatch_Date_time(rs.getString("match_date_time"));
                            m.setMain_refree(rs.getString("main_refree"));
                            m.setLinesman1(rs.getString("linesman1"));
                            m.setLinesman2(rs.getString("linesman2"));
                            m.setStadium_id(rs.getInt("stadium_id"));
                            return m;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error retrieving matches at /getMatches");
            System.out.println(e);
            return Collections.emptyList();
        }
    }



    /*
    This is an endpoint that creates a new match
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/createMatch")
    public ResponseEntity<Object> createMatch(@RequestBody Match match)
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();
        System.out.println("Trying to create match");
        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return new ResponseEntity<>("ACCESS DENIED!", HttpStatus.FORBIDDEN);
        String query = "INSERT INTO match_reservation_system.match VALUES (NULL, "
                + "'" + match.getHome_team() + "', "
                + "'" + match.getAway_team() + "', "
                + "'" + match.getMatch_date_time() + "', "
                + "'" + match.getMain_refree() + "', "
                + "'" + match.getLinesman1() + "', "
                + "'" + match.getLinesman2() + "', "
                + "'" + match.getStadium_id() + "');";

        try {
            jdbcTemplate.execute(query);
            System.out.println("Match created!");
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error adding match.", HttpStatus.FORBIDDEN);
        }

    }
    /*
    This endpoint enable user to edit match in database.
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/editMatch")
    public ResponseEntity<Object> edit(@RequestBody Match match) {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
        String user_type =  ((User)user).getUser_type();
        System.out.println("Trying to edit match");
        if(!user_type.equals("Manager") && !user_type.equals("Admin"))
            return new ResponseEntity<>("ACCESS DENIED!", HttpStatus.FORBIDDEN);

        String query = "UPDATE match_reservation_system.match SET "
                + "home_team = '" + match.getHome_team() + "', "
                + "away_team = '" + match.getAway_team() + "', "
                + "match_date_time = '" + match.getMatch_date_time() + "', "
                + "main_refree = '" + match.getMain_refree() + "', "
                + "linesman1 = '" + match.getLinesman1() + "', "
                + "linesman2 = '" + match.getLinesman2() + "', "
                + "stadium_id = '" + match.getStadium_id() + "' "
                + "WHERE match_id = '" + match.getMatch_id() + "';";
        try {
            System.out.println(query);
            jdbcTemplate.execute(query);
            System.out.println("Match Edited!");
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error editing Match", HttpStatus.CONFLICT);
        }
    }

}