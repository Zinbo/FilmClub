package com.zinbo.shared;

import com.zinbo.addmovie.Submitter;
import com.zinbo.votemovie.Vote;

import java.util.List;

public class Movie extends AggregateRoot {
    private String name;
    private String imdbId;
    private List<Vote> votes;
    private Submitter submitter;
    private String desc;

}
