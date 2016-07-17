package com.zinbo.shared;

import com.zinbo.addmovie.Submitter;
import com.zinbo.votemovie.Vote;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
public class Movie extends AggregateRoot {

    private String name;
    private String imdbId;
    //private List<Vote> votes;
    //private Submitter submitter;
    private String description;

    private Movie(){

    }

    public Movie(String name, String imdbId, String description){
        this.name = name;
        this.imdbId = imdbId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getImdbId() {
        return imdbId;
    }

    private void setImdbId(String imdbId){
        this.imdbId = imdbId;
    }
/*
    @OneToMany
    public List<Vote> getVotes() {
        return votes;
    }

    private void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @OneToOne
    public Submitter getSubmitter() {
        return submitter;
    }

    private void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }
*/
}
