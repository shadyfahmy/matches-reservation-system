package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.Res;
import com.tickets_reservation_system.entity.Reservation;
import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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


}
