package com.tickets_reservation_system.entity;

public class DetailedReservation {
    int ticket_id;
    int seat_number;
    int match_id;

    String home_team;
    String away_team;
    String match_date_time;
    String main_refree;
    String linesman1;
    String linesman2;
    String stadium_name;

    public DetailedReservation(){};

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setLinesman2(String linesman2) {
        this.linesman2 = linesman2;
    }

    public void setLinesman1(String linesman1) {
        this.linesman1 = linesman1;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public void setMain_refree(String main_refree) {
        this.main_refree = main_refree;
    }

    public void setMatch_date_time(String match_date_time) {
        this.match_date_time = match_date_time;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public void setStadium_name(String stadium_name) {
        this.stadium_name = stadium_name;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public String getAway_team() {
        return away_team;
    }

    public String getHome_team() {
        return home_team;
    }

    public String getLinesman1() {
        return linesman1;
    }

    public String getLinesman2() {
        return linesman2;
    }

    public String getMain_refree() {
        return main_refree;
    }

    public String getMatch_date_time() {
        return match_date_time;
    }

    public String getStadium_name() {
        return stadium_name;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getMatch_id() {
        return match_id;
    }
}
