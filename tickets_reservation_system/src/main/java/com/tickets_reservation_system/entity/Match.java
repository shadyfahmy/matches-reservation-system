package com.tickets_reservation_system.entity;

public class Match {
    int id;
    String home_team;
    String away_team;
    String date_time;
    String main_referee;
    String linesman1;
    String linesman2;
    int stadium_id;

    public Match(){};

    public Match(int id,String home_team,String away_team,String date_time,
                String main_referee,String linesman1,String linesman2,int stadium_id)
    {
        this.id=id;
        this.away_team=away_team;
        this.home_team=home_team;
        this.date_time=date_time;
        this.main_referee=main_referee;
        this.linesman1=linesman1;
        this.linesman2=linesman2;
        this.stadium_id=stadium_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setLinesman1(String linesman1) {
        this.linesman1 = linesman1;
    }

    public void setMain_referee(String main_referee) {
        this.main_referee = main_referee;
    }

    public void setLinesman2(String linesman2) {
        this.linesman2 = linesman2;
    }

    public void setStadium_id(int stadium_id) {
        this.stadium_id = stadium_id;
    }
}
