package com.tickets_reservation_system.entity;

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

    public int getId() {
        return id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public String getUsername() {
        return username;
    }
}
