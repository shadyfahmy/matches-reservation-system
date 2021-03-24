package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.Match;
import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/allmatches")
    public List<Match> listPending() {
        String query = "select * from match order by match_date_time desc";
        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Match>() {
                        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Match m = new Match();
                            m.setId(rs.getInt("match_id"));
                            m.setHome_team(rs.getString("home_team"));
                            m.setAway_team(rs.getString("away_team"));
                            m.setDate_time(rs.getDate("match_date_time").toString());
                            m.setMain_referee(rs.getString("main_refree"));
                            m.setLinesman1(rs.getString("linesman1"));
                            m.setLinesman2(rs.getString("linesman2"));
                            m.setStadium_id(rs.getInt("stadium_id"));
                            return m;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return Collections.emptyList();
        }
    }
}
