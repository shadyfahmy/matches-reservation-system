package com.tickets_reservation_system.entity;

public class Stadium {
    int id;
    String name;
    String city;
    int number_of_rows;
    int seats_per_row;

    public Stadium(){};



    public Stadium(int id, String name, String city, int number_of_rows, int seats_per_row) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.number_of_rows = number_of_rows;
        this.seats_per_row = seats_per_row;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber_of_rows() {
        return this.number_of_rows;
    }

    public void setNumber_of_rows(int number_of_rows) {
        this.number_of_rows = number_of_rows;
    }

    public int getSeats_per_row() {
        return this.seats_per_row;
    }

    public void setSeats_per_row(int seats_per_row) {
        this.seats_per_row = seats_per_row;
    }
    

}
