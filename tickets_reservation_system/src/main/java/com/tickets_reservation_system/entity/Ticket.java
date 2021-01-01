package com.search.tickets_reservation_system.entity;

public class Ticket {
    int id;
    int match_id;
    String username;
    int seat_num;

    public Ticket(){};

    public Ticket(int id,int match_id,String username,int seat_num)
    {
        this.id=id;
        this.match_id=match_id;
        this.username=username;
        this.seat_num=seat_num;
    }
}
