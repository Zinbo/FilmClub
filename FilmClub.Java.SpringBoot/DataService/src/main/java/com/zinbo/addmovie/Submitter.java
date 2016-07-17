package com.zinbo.addmovie;

import com.zinbo.shared.Entity;

import javax.persistence.Id;

@javax.persistence.Entity
public class Submitter extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
