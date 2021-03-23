package com.tickets_reservation_system.entity;

public class Match {
    int match_id;
    String home_team;
    String away_team;
    String match_date_time;
    String main_refree;
    String linesman1;
    String linesman2;
    int stadium_id;

    public Match(){};

    public Match(int match_id,String home_team,String away_team,String match_date_time,
                String main_refree,String linesman1,String linesman2,int stadium_id)
    {
        this.match_id=match_id;
        this.away_team=away_team;
        this.home_team=home_team;
        this.match_date_time=match_date_time;
        this.main_refree=main_refree;
        this.linesman1=linesman1;
        this.linesman2=linesman2;
        this.stadium_id=stadium_id;
    }


    public int getMatch_id() {
        return this.match_id;
    }

    public void setMatchId(int match_id) {
        this.match_id = match_id;
    }

    public String getHome_team() {
        return this.home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return this.away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getMatch_date_time() {
        return this.match_date_time;
    }

    public void setMatch_Date_time(String match_date_time) {
        this.match_date_time = match_date_time;
    }

    public String getMain_refree() {
        return this.main_refree;
    }

    public void setMain_refree(String main_refree) {
        this.main_refree = main_refree;
    }

    public String getLinesman1() {
        return this.linesman1;
    }

    public void setLinesman1(String linesman1) {
        this.linesman1 = linesman1;
    }

    public String getLinesman2() {
        return this.linesman2;
    }

    public void setLinesman2(String linesman2) {
        this.linesman2 = linesman2;
    }

    public int getStadium_id() {
        return this.stadium_id;
    }

    public void setStadium_id(int stadium_id) {
        this.stadium_id = stadium_id;
    }

}
