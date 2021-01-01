package com.tickets_reservation_system.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
public class Query {
    @Id
    String text;

    public Query(){}

    public Query(String txt) {
        this.text = txt;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
