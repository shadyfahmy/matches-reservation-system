package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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


}
