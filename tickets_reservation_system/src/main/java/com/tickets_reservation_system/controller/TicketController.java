package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TicketController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*Handling Socket messaging*/
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Res greeting(Res msg) throws Exception {
        System.out.println("recieved message");
        //Thread.sleep(1000); // simulated delay
        return new Res("refresh");
    }

    /*This endpoint returns reserved seats of a certain match*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/ticket/getreserved")
    public List<Ticket> getReserved(@RequestParam(name = "match") int match)
    {
        String query = "select * from ticket where match_id = " + Integer.toString(match) ;
        try {
            return jdbcTemplate.query(query,
                    new RowMapper<Ticket>() {
                        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Ticket t = new Ticket();
                            t.setId(rs.getInt("ticket_id"));
                            t.setSeat_number(rs.getInt("seat_number"));
                            t.setUsername(rs.getString("username"));
                            t.setMatch_id(rs.getInt("match_id"));
                            return t;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    /*Get all reservations by a certain user*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/ticket/getreservations")
    public List<DetailedReservation> getReservations()
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String user_name =  ((User)user).getUsername();

        String query = "select * from match_reservation_system.ticket t, "
                + "match_reservation_system.match m, match_reservation_system.stadium s where"
                + " t.username = '" + user_name
                + "' and t.match_id = m.match_id "
                + " and m.stadium_id = s.stadium_id";
        try {
            return jdbcTemplate.query(query,
                    new RowMapper<DetailedReservation>() {
                        public DetailedReservation mapRow(ResultSet rs, int rowNum) throws SQLException {
                            DetailedReservation t = new DetailedReservation();
                            t.setTicket_id(rs.getInt("ticket_id"));
                            t.setSeat_number(rs.getInt("seat_number"));
                            t.setHome_team(rs.getString("home_team"));
                            t.setAway_team(rs.getString("away_team"));
                            t.setLinesman1(rs.getString("linesman1"));
                            t.setLinesman2(rs.getString("linesman2"));
                            t.setMain_refree(rs.getString("main_refree"));
                            t.setStadium_name(rs.getString("name"));
                            t.setMatch_date_time(rs.getString("match_date_time"));
                            t.setMatch_id(rs.getInt("match_id"));
                            return t;
                        }
                    });
        }
        catch(Exception e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    /*Reserve one or more seats*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "api/ticket/reserve")
    public ResponseEntity<Object> createStadium(@RequestBody Reservation reservation)
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String user_name =  ((User)user).getUsername();

        System.out.println(reservation.getTickets().size());

        String query = "INSERT INTO match_reservation_system.ticket VALUES";

                for (int i = 0; i < reservation.getTickets().size(); i++) {
                    if(i == reservation.getTickets().size() - 1) {
                        query = query +" (NULL, " + "'" + user_name + "', "
                                + "'" + reservation.getTickets().get(i) + "', "
                                + "'" + reservation.getMatch() + "');"
                        ;
                    }
                    else {
                        query = query +" (NULL, " + "'" + user_name + "', "
                                + "'" + reservation.getTickets().get(i) + "', "
                                + "'" + reservation.getMatch() + "'),"

                    ;
                    }
                }
        System.out.println(query);

        try {
            jdbcTemplate.execute(query);
            System.out.println("Ticket reserved!");
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error reserving ticket.", HttpStatus.FORBIDDEN);
        }
    }

    /*Cancel certain reservation*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/ticket/cancelreservation")
    public ResponseEntity<Object> cancelReservation(@RequestParam(name = "match") int match,
                                                    @RequestParam(name = "seat") int seat)
    {

        String query = "DELETE FROM match_reservation_system.ticket where match_id = "
                + Integer.toString(match)
                + " AND seat_number = " + Integer.toString(seat)+"";

        System.out.println(query);

        try {
            jdbcTemplate.execute(query);
            System.out.println("Ticket Deleted!");
            return new ResponseEntity<Object>(new ArrayList<>(), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error canceling reservation.", HttpStatus.FORBIDDEN);
        }
    }


}
