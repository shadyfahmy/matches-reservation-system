package com.tickets_reservation_system.entity;

public class Ticket {
    int id;
    int match_id;
    String username;
    int seat_number;

    public Ticket(){};

    public Ticket(int id,int match_id,String username,int seat_number)
    {
        this.id=id;
        this.match_id=match_id;
        this.username=username;
        this.seat_number=seat_number;
    }

    public int getId() {
        return id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
