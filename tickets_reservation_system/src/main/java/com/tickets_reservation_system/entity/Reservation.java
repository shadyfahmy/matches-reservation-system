package com.tickets_reservation_system.entity;

import java.util.List;

public class Reservation {
    int match;
    List<Integer> tickets;

    public Reservation(){};
    public Reservation(int match, List<Integer> tickets) {
        this.match=match;
        this.tickets=tickets;
    }

    public int getMatch() {
        return match;
    }

    public List<Integer> getTickets() {
        return tickets;
    }
}
