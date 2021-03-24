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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Res greeting(Res msg) throws Exception {
        System.out.println("recieved message");
        //Thread.sleep(1000); // simulated delay
        return new Res("refresh");
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "api/ticket/reserve")
    public ResponseEntity<Object> createStadium(@RequestBody Reservation reservation)
    {
        // Get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Make sure user has access to this table
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


}
