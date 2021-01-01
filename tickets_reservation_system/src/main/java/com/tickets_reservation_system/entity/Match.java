package com.search.tickets_reservation_system.entity;

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
}
