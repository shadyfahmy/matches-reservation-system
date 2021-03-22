package com.tickets_reservation_system.entity;

import java.time.LocalDateTime;

public class Team {

    String team_name;

    public Team(){};

    public Team(String team_name)
    {
        this.team_name=team_name;
    }

    public String getTeam_name() {
        return this.team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

}

