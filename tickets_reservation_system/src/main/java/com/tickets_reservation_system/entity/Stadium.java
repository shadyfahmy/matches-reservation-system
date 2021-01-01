package com.tickets_reservation_system.entity;

public class Stadium {
    int id;
    String name;
    int rows;
    int seat_per_row;

    public Stadium(){};

    public Stadium(int id, String name, int rows, int seat_per_row)
    {
        this.id=id;
        this.name=name;
        this.rows=rows;
        this.seat_per_row=seat_per_row;
    }

}
